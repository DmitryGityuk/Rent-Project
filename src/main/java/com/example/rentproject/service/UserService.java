package com.example.rentproject.service;

import com.example.rentproject.models.Role;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.UserRepository;
import com.example.rentproject.utils.EncryptedPasswordUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final MailSenderService mailSender;


    public UserService(UserRepository userRepo, MailSenderService mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepo.findUserByUserEmail(userEmail);
    }

    public boolean regUser(User user) {
        User appUserFromDb = userRepo.findUserByUserEmail(user.getUserEmail());
        if (appUserFromDb != null) {
            return false;
        }
        user.setPassword(EncryptedPasswordUtils.encryptedPassword(user.getPassword()));
        user.setAccountIsActivated(false);
        user.setAccountNonLocked(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        sendActivationCode(user);
        return true;
    }

    private void sendActivationCode(User user) {
        if (!StringUtils.isEmpty(user.getUserEmail())) {
            String message = String.format(
                    "Добро пожаловать на SomeHomesBooking.com" + " Вы создали аккаунт с электронным адресом: %s \n" +
                            "Перейдите по http://localhost:8091/activate/%s для верификации этого электронного адреса и полного открытия аккаунта.",
                    user.getUserEmail(),
                    user.getActivationCode()
            );
            mailSender.sendRegistrationLetter(user.getUserEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User appUser = userRepo.findUserByActivationCode(code);
        if (appUser == null) {
            return false;
        }
        appUser.setActivationCode(null);
        appUser.setAccountIsActivated(true);
        userRepo.save(appUser);
        return true;
    }

    public void sendNews(String emailTo) {
        String message = String.format(
                "Вы %s подписались на новостную рассылку от SomeHomes.com" + " Вскоре вы начнёте получать важные и полезные новости.",
                emailTo);
        mailSender.sendNewsLetter(emailTo, "Новости", message);
    }

    public boolean regOwner(User user) {
        User appUserFromDb = userRepo.findUserByUserEmail(user.getUserEmail());
        if (appUserFromDb != null) {
            return false;
        }
        user.setPassword(EncryptedPasswordUtils.encryptedPassword(user.getPassword()));
        user.setAccountIsActivated(false);
        user.setAccountNonLocked(true);
        user.setRoles(Collections.singleton(Role.OWNER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        sendActivationCode(user);
        return true;
    }

    public void updateUser(Long id, User user) {
        User userFromDB = userRepo.findById(id).get();
        String email = user.getUserEmail();
        boolean isEmailChanged = (userFromDB.getUserEmail() != null && !userFromDB.getUserEmail().equals(email))
                || email != null && !email.equals(userFromDB.getUserEmail());
        if (isEmailChanged) {
            user.setUserEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!isEmailChanged) {
            user.setAccountIsActivated(true);
        }
        user.setPassword(EncryptedPasswordUtils.encryptedPassword(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setSecondName(user.getSecondName());
        userRepo.save(user);
        if (isEmailChanged) {
            sendActivationCode(user);
        }
    }
}
