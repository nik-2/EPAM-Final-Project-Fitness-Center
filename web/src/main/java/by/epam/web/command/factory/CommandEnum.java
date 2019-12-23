package by.epam.web.command.factory;

import by.epam.web.command.admin.*;
import by.epam.web.command.base.*;
import by.epam.web.command.coach.ViewCustomersCommand;
import by.epam.web.command.user.*;

/**
 * The enum Command enum.
 */
public enum CommandEnum {
    /**
     * The Login.
     */
    LOGIN {
        {
        this.command = new LoginCommand();
        }
    },
    /**
     * The Logout.
     */
    LOGOUT {
        {
        this.command = new LogoutCommand();
        }
    },
    /**
     * The Registration.
     */
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    /**
     * The Confirm.
     */
    CONFIRM {
        {
            this.command = new RegConfirmCommand();
        }
    },
    /**
     * The En.
     */
    EN {
        {
            this.command = new EnglishCommand();
        }
    },
    /**
     * The Ru.
     */
    RU {
        {
            this.command = new RussianCommand();
        }
    },
    /**
     * The Profile.
     */
    PROFILE{
        {
            this.command = new ProfileCommand();
        }
    },
    /**
     * The View.
     */
    VIEW{
        {
            this.command = new ViewCommand();
        }
    },
    /**
     * The Block.
     */
    BLOCK{
        {
            this.command = new BlockCommand();
        }
    },
    /**
     * The Unblock.
     */
    UNBLOCK{
        {
            this.command = new UnblockCommand();
        }
    },
    /**
     * The Change role.
     */
    CHANGE_ROLE{
        {
            this.command = new ChangeRoleCommand();
        }
    },
    /**
     * The Change user.
     */
    CHANGE_USER{
        {
            this.command = new ChangeUserCommand();
        }
    },
    /**
     * The View data.
     */
    VIEW_DATA{
        {
            this.command = new ViewDataCommand();
        }
    },
    /**
     * The Change password.
     */
    CHANGE_PASSWORD{
        {
            this.command = new ChangePasswordCommand();
        }
    },
    /**
     * The Change user data.
     */
    CHANGE_USER_DATA{
        {
            this.command = new ChangeUserDataCommand();
        }
    },
    /**
     * The View subscription.
     */
    VIEW_SUBSCRIPTION{
        {
            this.command = new ViewSubscriptionCommand();
        }
    },
    /**
     * The Link card.
     */
    LINK_CARD{
        {
            this.command = new LinkCardCommand();
        }
    },
    /**
     * The Replenish.
     */
    REPLENISH{
        {
            this.command = new ReplenishCommand();
        }
    },
    /**
     * The Change subscription coach.
     */
    CHANGE_SUBSCRIPTION_COACH{
        {
            this.command = new ChangeSubscriptionCoachCommand();
        }
    },
    /**
     * The Change subscription.
     */
    CHANGE_SUBSCRIPTION{
        {
            this.command = new ChangeSubscriptionCommand();
        }
    },
    /**
     * The View diet.
     */
    VIEW_DIET{
        {
            this.command = new ViewDietCommand();
        }
    },
    /**
     * The View exercise.
     */
    VIEW_EXERCISE{
        {
            this.command = new ViewExerciseCommand();
        }
    },
    /**
     * The Change user diet.
     */
    CHANGE_USER_DIET{
        {
            this.command = new ChangeUserDietCommand();
        }
    },
    /**
     * The Change user exercise.
     */
    CHANGE_USER_EXERCISE{
        {
            this.command = new ChangeUserExerciseCommand();
        }
    },
    /**
     * The View customers.
     */
    VIEW_CUSTOMERS{
        {
            this.command = new ViewCustomersCommand();
        }
    };
    /**
     * The Command.
     */
    ActionCommand command;

    /**
     * Gets current command.
     *
     * @return the current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
