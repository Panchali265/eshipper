import { INotificationTemplate } from 'app/shared/model/notification-template.model';

export interface INotificationMethod {
  id?: number;
  methodName?: string;
  notificationTemplates?: INotificationTemplate[];
}

export class NotificationMethod implements INotificationMethod {
  constructor(public id?: number, public methodName?: string, public notificationTemplates?: INotificationTemplate[]) {}
}
