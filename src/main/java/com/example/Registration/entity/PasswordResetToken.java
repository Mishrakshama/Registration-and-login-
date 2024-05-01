package com.example.Registration.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String token;

	    @OneToOne(fetch = FetchType.LAZY)
	    private User user;

	    private LocalDateTime expiryDate;

		public PasswordResetToken(Long id, String token, User user, LocalDateTime expiryDate) {
			super();
			this.id = id;
			this.token = token;
			this.user = user;
			this.expiryDate = expiryDate;
		}

		public PasswordResetToken() {
			
		}
		
		public boolean isExpired() {
	        return LocalDateTime.now().isAfter(expiryDate);
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public LocalDateTime getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(LocalDateTime expiryDate) {
			this.expiryDate = expiryDate;
		}
}
