package com.example.Parking.service.impl;

import com.example.Parking.dto.LoginEnterDTO;
import com.example.Parking.emu.AcountStatu;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.Token;
import com.example.Parking.outils.Config;
import com.example.Parking.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImp {

   private AccountRepository accountRepository;

   @Autowired
    public LoginServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //TODO effectuer une connection + faire des test unitaire pour les methode creer

    //effectuer la connexion
    public boolean login(@NotNull LoginEnterDTO loginEnterDTO) {
        Optional<Account> loginAccount = checkExistingAccountByEmailOrUsernameReturnIt(loginEnterDTO.getIdentifiant());
        if (loginAccount.isPresent() && Config.matches(loginEnterDTO.getPassword(), loginAccount.get().getPassword())) {
            return true;
        } else {
            throw new CustomeException("Échec de la connexion");
        }
    }

    //Verifier la connexion avec le mail ou le pseudo(usename)
    private Optional<Account> checkExistingAccountByEmailOrUsernameReturnIt(@NotNull String emailOrUsername) {
        //recupere le mail dans la base de donnee
        Optional<Account> existingAccountByEmail = accountRepository.findByEmail(emailOrUsername);
        //recupere le le nom dans la base de donnee
        Optional<Account> existingAccountByUsername = accountRepository.findByUsername(emailOrUsername);
        //condition de verification mail et nom s'il existe dans la DBB on retourne
        if (existingAccountByEmail.isPresent() && existingAccountByUsername.get().getStatus().name().equals(AcountStatu.ACTIVE)) {
            return existingAccountByEmail;
        } else if (existingAccountByUsername.isPresent() && existingAccountByUsername.get().getStatus().name().equals(AcountStatu.ACTIVE)) {
            return existingAccountByUsername;
        } else {
            throw new CustomeException("Aucun compte trouvé pour cet identifiant : " + emailOrUsername);
        }
    }

/*    public LoginSortant login(String email, String password) throws Exception {
        Optional<Utilisateur> optional = utilisateurRepository.findByEmail(email);
        Exception ex = new Exception("Erreur sur identifiant ou le mot de passe");
        if (optional.isPresent()) {
            Utilisateur utilisateur = optional.get();// recupere l'utilisateur;
            boolean ok = passwordEncoderService.verifier(password, utilisateur.getMdp());
            if (ok) {
                // generer le token
                Token token01 = genererToken();
                token01.setUtilisateur(utilisateur);// on affecte le token au utlisateur
                tokenRepository.save(token01);

                // cree la valeur de retour
                LoginSortant loginSortant = new LoginSortant();
                loginSortant.setIdentifiant(utilisateur.getIdentifiant());
                loginSortant.setEmail(utilisateur.getEmail());
                loginSortant.setNom(utilisateur.getNom());
                loginSortant.setPrenom(utilisateur.getPrenom());
                loginSortant.setNumero(utilisateur.getNumerotel());
                loginSortant.setRole(utilisateur.getRole().getNom());
                loginSortant.setToken(token01.getValeur());

                return loginSortant;
            } else {
                throw ex;
            }
        } else {
            throw ex;
        }
    }*/

    private Token genererToken() {
        UUID uuid = UUID.randomUUID();
        String valeurToken = uuid.toString(); // le token envoyer part le toString
        Calendar calendar = Calendar.getInstance(); // permet de recupere la date et l'heur actuelle
        // la durere du token
        // on le met 15min
        calendar.add(Calendar.MINUTE, 30); // la durer
        Date expirationDate = calendar.getTime(); // constrution de date
        return new Token(valeurToken, expirationDate);
    }


}
