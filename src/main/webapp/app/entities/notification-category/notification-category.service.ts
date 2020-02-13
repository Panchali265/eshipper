import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotificationCategory } from 'app/shared/model/notification-category.model';

type EntityResponseType = HttpResponse<INotificationCategory>;
type EntityArrayResponseType = HttpResponse<INotificationCategory[]>;

@Injectable({ providedIn: 'root' })
export class NotificationCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/notification-categories';

  constructor(protected http: HttpClient) {}

  create(notificationCategory: INotificationCategory): Observable<EntityResponseType> {
    return this.http.post<INotificationCategory>(this.resourceUrl, notificationCategory, { observe: 'response' });
  }

  update(notificationCategory: INotificationCategory): Observable<EntityResponseType> {
    return this.http.put<INotificationCategory>(this.resourceUrl, notificationCategory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotificationCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotificationCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
