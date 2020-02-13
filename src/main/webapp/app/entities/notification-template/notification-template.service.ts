import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotificationTemplate } from 'app/shared/model/notification-template.model';

type EntityResponseType = HttpResponse<INotificationTemplate>;
type EntityArrayResponseType = HttpResponse<INotificationTemplate[]>;

@Injectable({ providedIn: 'root' })
export class NotificationTemplateService {
  public resourceUrl = SERVER_API_URL + 'api/notification-templates';

  constructor(protected http: HttpClient) {}

  create(notificationTemplate: INotificationTemplate): Observable<EntityResponseType> {
    return this.http.post<INotificationTemplate>(this.resourceUrl, notificationTemplate, { observe: 'response' });
  }

  update(notificationTemplate: INotificationTemplate): Observable<EntityResponseType> {
    return this.http.put<INotificationTemplate>(this.resourceUrl, notificationTemplate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotificationTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotificationTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
