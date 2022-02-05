import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';
import { LoginService } from '../login.service';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: HttpClient) { }

  public getQuizzes(){
    return this.http.get(`${baseUrl}/quiz/getQuizzes`);
  }

  public addQuiz(quizData:any){
    return this.http.post(`${baseUrl}/quiz/addQuiz`,quizData);
  }

  public deleteQuiz(quizId:any){
    return this.http.delete(`${baseUrl}/quiz/deleteQuiz?quizId=`+quizId)
  }
}
