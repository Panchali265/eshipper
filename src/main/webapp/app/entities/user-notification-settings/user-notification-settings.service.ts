import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

type EntityResponseType = HttpResponse<IUserNotificationSettings>;
type EntityArrayResponseType = HttpResponse<IUserNotificationSettings[]>;

@Injectable({ providedIn: 'root' })
export class UserNotificationSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/user-notification-settings';

  constructor(protected http: HttpClient) {}

  create(userNotificationSettings: IUserNotificationSettings): Observable<EntityResponseType> {
    return this.http.post<IUserNotificationSettings>(this.resourceUrl, userNotificationSettings, { observe: 'response' });
  }

  update(userNotificationSettings: IUserNotificationSettings): Observable<EntityResponseType> {
    return this.http.put<IUserNotificationSettings>(this.resourceUrl, userNotificationSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserNotificationSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserNotificationSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
