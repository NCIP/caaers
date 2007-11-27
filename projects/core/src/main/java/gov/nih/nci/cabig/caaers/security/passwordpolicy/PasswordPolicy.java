package gov.nih.nci.cabig.caaers.security.passwordpolicy;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;

@Entity
@Table(name="password_policy")
public class PasswordPolicy {

    private PasswordCreationPolicy passwordCreationPolicy;
    private LoginPolicy loginPolicy;
    private String hashAlgorithm;

    @Embedded
    @AttributeOverrides({
	@AttributeOverride(name="minPasswordAge", column=@Column(name="cn_min_age")),
	@AttributeOverride(name="passwordHistorySize", column=@Column(name="cn_history_size")),
	@AttributeOverride(name="minPasswordLength", column=@Column(name="cn_min_length")),
	@AttributeOverride(name="combinationPolicy.minimumRequired", column=@Column(name="cn_cb_min_required")),
	@AttributeOverride(name="combinationPolicy.upperCaseAlphabetRequired", column=@Column(name="cn_cb_is_upper_case_alphabet")),
	@AttributeOverride(name="combinationPolicy.lowerCaseAlphabetRequired", column=@Column(name="cn_cb_is_lower_case_alphabet")),
	@AttributeOverride(name="combinationPolicy.nonAlphaNumericRequired", column=@Column(name="cn_cb_is_non_alpha_numeric")),
	@AttributeOverride(name="combinationPolicy.baseTenDigitRequired", column=@Column(name="cn_cb_is_base_ten_digit")),
	@AttributeOverride(name="combinationPolicy.maxSubstringLength", column=@Column(name="cn_cb_max_substring_length"))})
    public PasswordCreationPolicy getPasswordCreationPolicy() {
	return passwordCreationPolicy;
    }

    public void setPasswordCreationPolicy(PasswordCreationPolicy passwordCreationPolicy) {
	this.passwordCreationPolicy = passwordCreationPolicy;
    }
    
    @Embedded
    @AttributeOverrides({
	@AttributeOverride(name="allowedFailedLoginAttempts", column=@Column(name="ln_allowed_attempts")),
	@AttributeOverride(name="lockOutDuration", column=@Column(name="ln_lockout_duration")),
	@AttributeOverride(name="maxPasswordAge", column=@Column(name="ln_max_age"))})
    public LoginPolicy getLoginPolicy() {
	return loginPolicy;
    }

    public void setLoginPolicy(LoginPolicy loginPolicy) {
	this.loginPolicy = loginPolicy;
    }

    @Column(name="hash_algorithm")
    public String getHashAlgorithm() {
	return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
	this.hashAlgorithm = hashAlgorithm;
    }
}
