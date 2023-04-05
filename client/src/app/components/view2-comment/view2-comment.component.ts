import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchReviewService } from 'src/app/services/search-review.service';
import { MovieComment } from 'src/models/reviews';

@Component({
  selector: 'app-view2-comment',
  templateUrl: './view2-comment.component.html',
  styleUrls: ['./view2-comment.component.css']
})
export class View2CommentComponent implements OnInit{
  ratings= ["1","2","3","4","5"];
  commentForm!:FormGroup
  title!:string;
  movieComment!:MovieComment;

  constructor(private fb: FormBuilder,
              private route:ActivatedRoute,
              private postSvc: SearchReviewService,
              private router: Router){}

  ngOnInit(): void {
    this.commentForm = this.createForm();
    this.route.queryParams.subscribe(
                          (x)=>{{this.title = x['title']}}
    )
  }

  private createForm(){
    const grp = this.fb.group({
      name: this.fb.control<string>(""),
      rating: this.fb.control<string>(this.ratings[0]),
      comment: this.fb.control<string>("")
    });
    return grp;
  }

  processForm(){
    console.log(this.commentForm.value)
    
    const form = Object.assign({title: this.title}, this.commentForm.value)

    this.postSvc.postComment(form).subscribe()

    //re-route
    this.router.navigate(['/search', this.title])
  }
}
