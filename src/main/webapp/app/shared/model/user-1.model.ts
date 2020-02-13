import { IWebNotification } from 'app/shared/model/web-notification.model';
import { IEmailNotification } from 'app/shared/model/email-notification.model';
import { IUserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

export interface IUser1 {
  id?: number;
  webNotifications?: IWebNotification[];
  webNotifications?: IWebNotification[];
  emailNotifications?: IEmailNotification[];
  emailNotifications?: IEmailNotification[];
  userNotificationSettings?: IUserNotificationSettings[];
}

export class User1 implements IUser1 {
  constructor(
    public id?: number,
    public webNotifications?: IWebNotification[],
    public webNotifications?: IWebNotification[],
    public emailNotifications?: IEmailNotification[],
    public emailNotifications?: IEmailNotification[],
    public userNotificationSettings?: IUserNotificationSettings[]
  ) {}
}
