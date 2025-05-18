import {Component, OnInit} from '@angular/core';
import {NgIf} from '@angular/common';
import {Goal} from './model/Goal';
import {GoalService} from './service/GoalService';

@Component({
  selector: 'app-root',
  imports: [
    NgIf
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  CRYPTO_BTC = '1FjcYxrht4V1v3rTuFCGoabFR54zVCic8u';
  CRYPTO_USDT = 'TV1XNe7WgwetjQsCiDhTXMJchwdFUZTuor';

  progressGoalDonates: string = '';
  progressGoalPercent: string= '';
  copiedAddress: string = '';

  goal: Goal | null = null;

  constructor(
    private goalService: GoalService
  ) {
    window.addEventListener('scroll', this.revealOnScroll);
    window.addEventListener('DOMContentLoaded', this.revealOnScroll);
  }

  ngOnInit(): void {
    this.goalService.getLastGoal().subscribe((data) => {
      this.goal = data;
      this.initGoal(data);
    });
  }

  initGoal(goal: Goal) {
    const currentAmount = goal.donate; // текущая сумма
    const goalAmount = goal.value; // цель

    this.progressGoalPercent = Math.min((currentAmount / goalAmount) * 100, 100) + '%';
    this.progressGoalDonates = `${currentAmount.toLocaleString('ru-RU')} ₽ / ${goalAmount.toLocaleString('ru-RU')} ₽`;
  }

  copyCrypto(address: string): void {
    navigator.clipboard.writeText(address).then(() => {
      this.copiedAddress = address; // Устанавливаем адрес как скопированный
      setTimeout(() => {
        this.copiedAddress = ''; // Сбрасываем через 1.2 секунды
      }, 1200);
    });
  }

  // Анимация появления карточек при прокрутке
  revealOnScroll() {
    const cards = document.querySelectorAll('.glass');
    const trigger = window.innerHeight * 0.92;
    cards.forEach(card => {
      const rect = card.getBoundingClientRect();
      if (rect.top < trigger) {
        card.classList.add('visible');
      }
    });
  }
}
