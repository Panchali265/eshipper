import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotificationMethod } from 'app/shared/model/notification-method.model';

type EntityResponseType = HttpResponse<INotificationMethod>;
type EntityArrayResponseType = HttpResponse<INotificationMethod[]>;

@Injectable({ providedIn: 'root' })
export class NotificationMethodService {
  public resourceUrl = SERVER_API_URL + 'api/notification-methods';

  constructor(protected http: HttpClient) {}

  create(notificationMethod: INotificationMethod): Observable<EntityResponseType> {
    return this.http.post<INotificationMethod>(this.resourceUrl, notificationMethod, { observe: 'response' });
  }

  update(notificationMethod: INotificationMethod): Observable<EntityResponseType> {
    return this.http.put<INotificationMethod>(this.resourceUrl, notificationMethod, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotificationMethod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotificationMethod[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
