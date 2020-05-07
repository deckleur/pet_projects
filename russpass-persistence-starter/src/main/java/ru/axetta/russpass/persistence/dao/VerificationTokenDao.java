package ru.axetta.russpass.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.axetta.russpass.persistence.domain.User;
import ru.axetta.russpass.persistence.domain.VerificationToken;

@Repository
public interface VerificationTokenDao extends JpaRepository<VerificationToken, Long> {

    @Query
    VerificationToken findByUserIdAndTokenType(Long userId, VerificationToken.Type tokenType);

    @Transactional
    default void findByUserAndDelete(User user, VerificationToken.Type tokenType) {
        VerificationToken verificationToken = findByUserIdAndTokenType(user.getId(), tokenType);
        if(verificationToken != null) {
            this.delete(verificationToken);
        }
    }

    @Query
    VerificationToken findByTokenAndTokenType(String token, VerificationToken.Type tokenType);
}
