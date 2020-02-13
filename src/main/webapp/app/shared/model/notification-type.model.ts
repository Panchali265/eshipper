import { INotificationTemplate } from 'app/shared/model/notification-template.model';

export interface INotificationType {
  id?: number;
  name?: string;
  enumName?: string;
  notificationTemplates?: INotificationTemplate[];
  notificationCategoryId?: number;
}

export class NotificationType implements INotificationType {
  constructor(
    public id?: number,
    public name?: string,
    public enumName?: string,
    public notificationTemplates?: INotificationTemplate[],
    public notificationCategoryId?: number
  ) {}
}
