export interface IEmailNotification {
  id?: number;
  toEmail?: string;
  emailMap?: string;
  notificationTemplateId?: number;
  createdById?: number;
  toUserId?: number;
}

export class EmailNotification implements IEmailNotification {
  constructor(
    public id?: number,
    public toEmail?: string,
    public emailMap?: string,
    public notificationTemplateId?: number,
    public createdById?: number,
    public toUserId?: number
  ) {}
}
