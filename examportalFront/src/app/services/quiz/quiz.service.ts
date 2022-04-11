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

  public getQuiz(quizId:number){
    return this.http.get(`${baseUrl}/quiz/getQuiz?quizId=`+quizId);
  }
  public addQuiz(quizData:any){
    return this.http.post(`${baseUrl}/quiz/addQuiz`,quizData);
  }

  public deleteQuiz(quizId:any){
    return this.http.delete(`${baseUrl}/quiz/deleteQuiz?quizId=`+quizId)
  }

  public updateQuiz(quizData:any){
    return this.http.put(`${baseUrl}/quiz/updateQuiz`,quizData);
  }

  public getQuizzesForACategory(catId:any){
    return this.http.get(`${baseUrl}/quiz/getQuizzesForACategory?catId=`+catId);
  }

  public getQuizzesByActive(){
    return this.http.get(`${baseUrl}/quiz/getQuizzesByActive`);
  }

  public getQuizzesForACategoryAndActive(catId:any){
    return this.http.get(`${baseUrl}/quiz/getQuizzesForACategoryAndActive?catId=`+catId);
  }
}
