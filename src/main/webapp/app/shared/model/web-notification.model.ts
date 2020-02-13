export interface IWebNotification {
  id?: number;
  url?: string;
  isRead?: boolean;
  webMap?: string;
  notificationTemplateId?: number;
  createdById?: number;
  toUserId?: number;
}

export class WebNotification implements IWebNotification {
  constructor(
    public id?: number,
    public url?: string,
    public isRead?: boolean,
    public webMap?: string,
    public notificationTemplateId?: number,
    public createdById?: number,
    public toUserId?: number
  ) {
    this.isRead = this.isRead || false;
  }
}
