import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Goal} from '../model/Goal';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GoalService {

  private readonly apiUrl = environment.API_URL;

  constructor(private http: HttpClient) {
  }

  getLastGoal(): Observable<Goal> {
    return this.http.get<Goal>(this.apiUrl + "/goals/last");
  }
}
