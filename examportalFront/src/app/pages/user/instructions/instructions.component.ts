import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  quizId:any;
  quizData:any;
  constructor(private route:ActivatedRoute, private quiz: QuizService, private router: Router) { }

  ngOnInit(): void {
    this.quizId=this.route.snapshot.params["quizId"];
    
    this.quiz.getQuiz(this.quizId).subscribe(
      (data)=>{
        this.quizData=data;
        console.log(data);
      },
      (error)=>{
        console.log(error);
        alert("Error on loading");
      }
    )
  }

  startQuiz(){
    Swal.fire({
      title: 'Are you sure you want to start?',
      showDenyButton: true,
      // showCancelButton: true,
      confirmButtonText: 'Yes',
      // denyButtonText: `No`,
      icon:'question' 
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.router.navigate(['/start/'+this.quizId]);
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

    

  

}
