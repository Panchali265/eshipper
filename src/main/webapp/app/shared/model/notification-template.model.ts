export interface INotificationTemplate {
  id?: number;
  subject?: string;
  description?: string;
  notificationMethodId?: number;
  notificationTypeId?: number;
}

export class NotificationTemplate implements INotificationTemplate {
  constructor(
    public id?: number,
    public subject?: string,
    public description?: string,
    public notificationMethodId?: number,
    public notificationTypeId?: number
  ) {}
}
