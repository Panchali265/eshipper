export interface IUserNotificationSettings {
  id?: number;
  web?: boolean;
  email?: boolean;
  configurableMap?: string;
  userId?: number;
}

export class UserNotificationSettings implements IUserNotificationSettings {
  constructor(public id?: number, public web?: boolean, public email?: boolean, public configurableMap?: string, public userId?: number) {
    this.web = this.web || false;
    this.email = this.email || false;
  }
}
