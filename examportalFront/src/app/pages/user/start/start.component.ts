import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions/questions.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  quizId:any;

  constructor(private locationSt: LocationStrategy, 
    private route: ActivatedRoute,
    private ques:QuestionsService) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.quizId = this.route.snapshot.params['quizId'];
  }

  preventBackButton(){
    history.pushState(null, "", location.href);
    this.locationSt.onPopState(()=>{
      history.pushState(null, "", location.href);
    })
  }
}
