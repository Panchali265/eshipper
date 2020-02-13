import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmailNotification } from 'app/shared/model/email-notification.model';

type EntityResponseType = HttpResponse<IEmailNotification>;
type EntityArrayResponseType = HttpResponse<IEmailNotification[]>;

@Injectable({ providedIn: 'root' })
export class EmailNotificationService {
  public resourceUrl = SERVER_API_URL + 'api/email-notifications';

  constructor(protected http: HttpClient) {}

  create(emailNotification: IEmailNotification): Observable<EntityResponseType> {
    return this.http.post<IEmailNotification>(this.resourceUrl, emailNotification, { observe: 'response' });
  }

  update(emailNotification: IEmailNotification): Observable<EntityResponseType> {
    return this.http.put<IEmailNotification>(this.resourceUrl, emailNotification, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEmailNotification>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEmailNotification[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
