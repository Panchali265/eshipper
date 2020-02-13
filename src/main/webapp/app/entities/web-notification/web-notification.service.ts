import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWebNotification } from 'app/shared/model/web-notification.model';

type EntityResponseType = HttpResponse<IWebNotification>;
type EntityArrayResponseType = HttpResponse<IWebNotification[]>;

@Injectable({ providedIn: 'root' })
export class WebNotificationService {
  public resourceUrl = SERVER_API_URL + 'api/web-notifications';

  constructor(protected http: HttpClient) {}

  create(webNotification: IWebNotification): Observable<EntityResponseType> {
    return this.http.post<IWebNotification>(this.resourceUrl, webNotification, { observe: 'response' });
  }

  update(webNotification: IWebNotification): Observable<EntityResponseType> {
    return this.http.put<IWebNotification>(this.resourceUrl, webNotification, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWebNotification>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWebNotification[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
