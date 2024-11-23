import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private wsUrl = 'ws://localhost:8082/ws';
  private socket$: WebSocketSubject<string> | null = null;

  private alertsSubject = new BehaviorSubject<string[]>([]);
  public alerts$ = this.alertsSubject.asObservable();

  connect() {
    if (!this.socket$ || this.socket$.closed) {
      this.socket$ = webSocket(this.wsUrl);

      this.socket$.subscribe(
        (message: string) => {
          console.log('Received message:', message);
          this.addAlert(message); // Nu mai încerca să faci parse la JSON
        },
        (error) => {
          console.error('WebSocket error:', error);
        },
        () => {
          console.log('WebSocket connection closed');
        }
      );
    }
  }

  private addAlert(alert: string) {
    const currentAlerts = this.alertsSubject.getValue();
    this.alertsSubject.next([...currentAlerts, alert]);
  }

  disconnect() {
    this.socket$?.complete();
    console.log('WebSocket disconnected');
  }
}
