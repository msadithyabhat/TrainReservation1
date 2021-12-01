import { SelectionModel } from '@angular/cdk/collections';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Train } from '../train';
import { TrainService } from '../train.service';
import { Ticket } from '../ticket';
import { MatSortHeader } from '@angular/material/sort';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
// import { TicketService } from '../ticket.service';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';



@Component({
  selector: 'app-print-ticket',
  templateUrl: './print-ticket.component.html',
  styleUrls: ['./print-ticket.component.css'],
  styles: [`
    .dark-modal .modal-content {
      background-color: #292b2c;
      color: white;
    }
    .dark-modal .close {
      color: white;
    }
    .light-blue-backdrop {
      background-color: #5cb3fd;
    }
  `]
})



export class PrintTicketComponent implements OnInit{
  closeResult!: string; 

dataSaved = false;
allTrain!: Observable<Train[]>;
dataSource!: MatTableDataSource<Train>;
dataSource1!:MatTableDataSource<Ticket>;
selection = new SelectionModel<Train>(true, []);
trainIdUpdate: String | undefined;
massage = null;
SelectedDate = null;
horizontalPosition: MatSnackBarHorizontalPosition = 'center';
verticalPosition: MatSnackBarVerticalPosition = 'bottom';
displayedColumns: string[] = ['id','pnr','trainName','source','destination','name','age','gender','travelDate','ticketprice','Print'];
  ticketForm: any;
  ticketIdUpdate!: String;
  id: any;


ngOnInit(){
  this.ticketForm=this.formbulider.group({
    pnr: [''],
    trainName: [''],
    source: [''],
    destination: [''], 
    name: [''],
    age: [''],
    gender: [''],
    travelDate: [''],
    ticketprice:[''],
  })
  this.loadAllTicket();
}


  constructor(private formbulider: FormBuilder, private TrainService: TrainService,public dialog: MatDialog,private modalService: NgbModal) {}
  

  loadAllTicket(){
    this.TrainService.getAllTicket().subscribe(data => {
      this.dataSource1 = new MatTableDataSource(data);
      console.log(this.dataSource1);
      
    });
  }

  openWindowCustomClass(content: any,id:String) {
    console.log(id);
    this.id=id;
   this. loadPassengerToPrint(this.id);
    this.modalService.open(content, { windowClass: 'dark-modal' });
  }

  loadPassengerToPrint(id: string) {
    console.log();
    this.id=id;
    this.TrainService.getTicketById(id).subscribe(Ticket => {
      console.log(Ticket);
      
      this.massage = null;
      this.dataSaved = false;
      // this.passengerIdUpdate = Passenger.id;
      this.ticketForm.controls['pnr'].setValue(Ticket.pnr);
      this.ticketForm.controls['trainName'].setValue(Ticket.trainName);
      this.ticketForm.controls['source'].setValue(Ticket.source);
      this.ticketForm.controls['destination'].setValue(Ticket.destination);
      this.ticketForm.controls['name'].setValue(Ticket.name);
      this.ticketForm.controls['age'].setValue(Ticket.age);
      this.ticketForm.controls['gender'].setValue(Ticket.gender);
      this.ticketForm.controls['travelDate'].setValue(Ticket.travelDate);
      this.ticketForm.controls['ticketprice'].setValue(Ticket.ticketprice);
      
    });
    
  }
  @ViewChild("screen")
  screen!: ElementRef;
  @ViewChild("canvas")
  canvas!: ElementRef;
  @ViewChild("downloadLink")
  downloadLink!: ElementRef;
canavas!:any;
  downloadImage() {
    html2canvas(this.screen.nativeElement).then(canvas => {
      this.canvas.nativeElement.src = canvas.toDataURL();
      this.downloadLink.nativeElement.href = canvas.toDataURL("image/png");
      // this.downloadLink.nativeElement.download = "Ticket.png";
      // this.downloadLink.nativeElement.click();
      var imgWidth = 208;   
      var pageHeight = 295;    
      var imgHeight = canvas.height * imgWidth / canvas.width;  
      var heightLeft = imgHeight;  
  
      const contentDataURL = canvas.toDataURL('image/png')  
      let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
      var position = 0;  
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)  
      pdf.save('MYPdf.pdf'); // Generated PDF 
    });
  }

  
}
  

