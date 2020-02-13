import { INotificationType } from 'app/shared/model/notification-type.model';

export interface INotificationCategory {
  id?: number;
  name?: string;
  role?: string;
  notificationTypes?: INotificationType[];
}

export class NotificationCategory implements INotificationCategory {
  constructor(public id?: number, public name?: string, public role?: string, public notificationTypes?: INotificationType[]) {}
}
