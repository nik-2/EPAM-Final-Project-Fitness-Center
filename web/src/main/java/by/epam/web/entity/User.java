package by.epam.web.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type User.
 */
public class User {
    private static final Logger logger = LogManager.getLogger(User.class);

    private String mail;
    private String name;
    private String surname;
    private String password;
    private Role role;
    private String bankCardId;
    private int clubBalance;
    private int cardBalance;
    private boolean block;
    private Status status;
    private String code;
    private String dateReg;
    private String dateSub;
    private Subscription subscription;
    private String diet;
    private String exercise;

    /**
     * Instantiates a new User.
     */
    public User() {
        logger.debug("Create new user");
    }

    /**
     * Instantiates a new User.
     *
     * @param mail     the mail
     * @param name     the name
     * @param surname  the surname
     * @param password the password
     */
    public User(String mail, String name, String surname, String password) {
        logger.debug("Create new user with mail: " + mail);
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets bank card id.
     *
     * @return the bank card id
     */
    public String getBankCardId() {
        return bankCardId;
    }

    /**
     * Sets bank card id.
     *
     * @param bankCardId the bank card id
     */
    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    /**
     * Gets club balance.
     *
     * @return the club balance
     */
    public int getClubBalance() {
        return clubBalance;
    }

    /**
     * Sets club balance.
     *
     * @param clubBalance the club balance
     */
    public void setClubBalance(int clubBalance) {
        this.clubBalance = clubBalance;
    }

    /**
     * Gets card balance.
     *
     * @return the card balance
     */
    public int getCardBalance() {
        return cardBalance;
    }

    /**
     * Sets card balance.
     *
     * @param cardBalance the card balance
     */
    public void setCardBalance(int cardBalance) {
        this.cardBalance = cardBalance;
    }

    /**
     * Is block boolean.
     *
     * @return the boolean
     */
    public boolean isBlock() {
        return block;
    }

    /**
     * Sets block.
     *
     * @param block the block
     */
    public void setBlock(boolean block) {
        this.block = block;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets date reg.
     *
     * @return the date reg
     */
    public String getDateReg() {
        return dateReg;
    }

    /**
     * Sets date reg.
     *
     * @param dateReg the date reg
     */
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }

    /**
     * Gets date sub.
     *
     * @return the date sub
     */
    public String getDateSub() {
        return dateSub;
    }

    /**
     * Sets date sub.
     *
     * @param dateSub the date sub
     */
    public void setDateSub(String dateSub) {
        this.dateSub = dateSub;
    }

    /**
     * Gets subscription.
     *
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets subscription.
     *
     * @param subscription the subscription
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Gets diet.
     *
     * @return the diet
     */
    public String getDiet() {
        return diet;
    }

    /**
     * Sets diet.
     *
     * @param diet the diet
     */
    public void setDiet(String diet) {
        this.diet = diet;
    }

    /**
     * Gets exercise.
     *
     * @return the exercise
     */
    public String getExercise() {
        return exercise;
    }

    /**
     * Sets exercise.
     *
     * @param exercise the exercise
     */
    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
