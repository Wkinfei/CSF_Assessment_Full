import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchReviewService } from 'src/app/services/search-review.service';
import { Review } from 'src/models/reviews';

@Component({
  selector: 'app-view1-display',
  templateUrl: './view1-display.component.html',
  styleUrls: ['./view1-display.component.css']
})
export class View1DisplayComponent implements OnInit{
  query!:string
  reviews!: Review[]

 
  constructor(private route: ActivatedRoute,
              private searchSvc: SearchReviewService,
              private router: Router){}
  
  ngOnInit(): void {
    this.route.params.subscribe(
      (x)=>{
        this.query = x['query'];
        this.searchSvc.getReviews(this.query)
                      .subscribe((data)=>{this.reviews = data.reviews})
      }
    )
  }


}
