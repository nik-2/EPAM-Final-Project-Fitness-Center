package by.epam.web.command.factory;

import by.epam.web.command.admin.*;
import by.epam.web.command.base.*;
import by.epam.web.command.coach.ViewCustomersCommand;
import by.epam.web.command.user.*;

public enum CommandEnum {
    LOGIN {
        {
        this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
        this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    CONFIRM {
        {
            this.command = new RegConfirmCommand();
        }
    },
    EN {
        {
            this.command = new EnglishCommand();
        }
    },
    RU {
        {
            this.command = new RussianCommand();
        }
    },
    PROFILE{
        {
            this.command = new ProfileCommand();
        }
    },
    VIEW{
        {
            this.command = new ViewCommand();
        }
    },
    BLOCK{
        {
            this.command = new BlockCommand();
        }
    },
    UNBLOCK{
        {
            this.command = new UnblockCommand();
        }
    },
    CHANGE_ROLE{
        {
            this.command = new ChangeRoleCommand();
        }
    },
    CHANGE_USER{
        {
            this.command = new ChangeUserCommand();
        }
    },
    VIEW_DATA{
        {
            this.command = new ViewDataCommand();
        }
    },
    CHANGE_PASSWORD{
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_USER_DATA{
        {
            this.command = new ChangeUserDataCommand();
        }
    },
    VIEW_SUBSCRIPTION{
        {
            this.command = new ViewSubscriptionCommand();
        }
    },
    LINK_CARD{
        {
            this.command = new LinkCardCommand();
        }
    },
    REPLENISH{
        {
            this.command = new ReplenishCommand();
        }
    },
    CHANGE_SUBSCRIPTION_COACH{
        {
            this.command = new ChangeSubscriptionCoachCommand();
        }
    },
    CHANGE_SUBSCRIPTION{
        {
            this.command = new ChangeSubscriptionCommand();
        }
    },
    VIEW_DIET{
        {
            this.command = new ViewDietCommand();
        }
    },
    VIEW_EXERCISE{
        {
            this.command = new ViewExerciseCommand();
        }
    },
    CHANGE_USER_DIET{
        {
            this.command = new ChangeUserDietCommand();
        }
    },
    CHANGE_USER_EXERCISE{
        {
            this.command = new ChangeUserExerciseCommand();
        }
    },
    VIEW_CUSTOMERS{
        {
            this.command = new ViewCustomersCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
