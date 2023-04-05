import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { View0SearchComponent } from './components/view0-search/view0-search.component';
import { View1DisplayComponent } from './components/view1-display/view1-display.component';
import { View2CommentComponent } from './components/view2-comment/view2-comment.component';

@NgModule({
  declarations: [
    AppComponent,
    View0SearchComponent,
    View1DisplayComponent,
    View2CommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
