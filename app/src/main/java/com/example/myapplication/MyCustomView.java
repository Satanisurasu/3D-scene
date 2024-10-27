package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {

    private Paint paint;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Задний фон
        canvas.drawColor(Color.WHITE);

        // Рисуем траву
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, getHeight() / 2, getWidth(), getHeight(), paint);

        // Рисуем дом
        paint.setColor(Color.rgb(139, 69, 19)); // Коричневый цвет для дома
        int houseLeft = getWidth() / 3;
        int houseTop = getHeight() / 2 - 200;
        int houseRight = 2 * getWidth() / 3;
        int houseBottom = getHeight() / 2 + 100;
        canvas.drawRect(houseLeft, houseTop, houseRight, houseBottom, paint);

        // Рисуем крышу
        paint.setColor(Color.LTGRAY);
        canvas.drawPath(getRoofPath(houseLeft, houseTop, houseRight), paint);

        // Рисуем квадратное окно
        paint.setColor(Color.DKGRAY);
        int windowLeft = houseLeft + 40;
        int windowTop = houseTop + 40;
        int windowRight = windowLeft + 80;
        int windowBottom = windowTop + 80;
        canvas.drawRect(windowLeft, windowTop, windowRight, windowBottom, paint);

        // Рисуем решетку на квадратном окне
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        int gridSize = 20;
        for (int i = windowLeft; i <= windowRight; i += gridSize) {
            canvas.drawLine(i, windowTop, i, windowBottom, paint);
        }
        for (int i = windowTop; i <= windowBottom; i += gridSize) {
            canvas.drawLine(windowLeft, i, windowRight, i, paint);
        }

        // Рисуем круглое окно слева от двери
        paint.setColor(Color.DKGRAY);
        int circleCenterX = houseLeft + 80; // Центр по X
        int circleCenterY = houseTop + 200; // Центр по Y (ниже крыши)
        int circleRadius = 50;
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, paint);

        // Рисуем решетку на круглом окне
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        for (int angle = 0; angle < 360; angle += 30) {
            float startX = circleCenterX + circleRadius * (float) Math.cos(Math.toRadians(angle));
            float startY = circleCenterY + circleRadius * (float) Math.sin(Math.toRadians(angle));
            float endX = circleCenterX + (circleRadius - 50) * (float) Math.cos(Math.toRadians(angle));
            float endY = circleCenterY + (circleRadius - 50) * (float) Math.sin(Math.toRadians(angle));
            canvas.drawLine(startX, startY, endX, endY, paint);
        }

        // Рисуем дверь
        paint.setColor(Color.BLACK);
        int doorLeft = houseRight - 80; // Делаем дверь шире
        int doorRight = houseRight - 20;
        int doorTop = houseBottom - 120;
        canvas.drawRect(doorLeft, doorTop, doorRight, houseBottom, paint);

        // Добавляем ручку на дверь
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(doorRight - 10, doorTop + 60, 5, paint);

        // Рисуем солнце
        paint.setColor(Color.YELLOW);
        int sunRadius = 50;
        int sunX = 100;
        int sunY = 100;
        canvas.drawCircle(sunX, sunY, sunRadius, paint);
        // Лучи солнца
        for (int i = 0; i < 360; i += 30) {
            float startX = sunX + sunRadius * (float) Math.cos(Math.toRadians(i));
            float startY = sunY + sunRadius * (float) Math.sin(Math.toRadians(i));
            float endX = sunX + (sunRadius + 30) * (float) Math.cos(Math.toRadians(i));
            float endY = sunY + (sunRadius + 30) * (float) Math.sin(Math.toRadians(i));
            canvas.drawLine(startX, startY, endX, endY, paint);
        }

        // Рисуем деревья
        drawTree(canvas, getWidth() / 5, getHeight() / 2 + 50);
        drawTree(canvas, 4 * getWidth() / 5, getHeight() / 2 + 50);

        // Рисуем скамейку
        drawBench(canvas, getWidth() - 150, getHeight() / 2 + 70);
    }

    // Метод для рисования крыши
    private Path getRoofPath(int left, int top, int right) {
        Path path = new Path();
        path.moveTo(left, top);
        path.lineTo((left + right) / 2, top - 100);
        path.lineTo(right, top);
        path.close();
        return path;
    }

    // Метод для рисования увеличенного дерева
    private void drawTree(Canvas canvas, int x, int y) {
        // Ствол дерева - темно-коричневый и выше, чем раньше
        paint.setColor(Color.rgb(101, 67, 33));
        int trunkWidth = 20;
        int trunkHeight = 100;
        canvas.drawRect(x - trunkWidth / 2, y, x + trunkWidth / 2, y + trunkHeight, paint);

        // Крона дерева - более крупный темно-зеленый круг
        paint.setColor(Color.rgb(34, 139, 34));
        int crownRadius = 60;
        canvas.drawCircle(x, y - 40, crownRadius, paint);
    }

    // Метод для рисования скамейки
    private void drawBench(Canvas canvas, int x, int y) {
        // Цвет скамейки - коричневый
        paint.setColor(Color.rgb(139, 69, 19));

        // Верхняя часть скамейки (сиденье)
        canvas.drawRect(x, y, x + 80, y + 10, paint);

        // Ножки скамейки
        canvas.drawRect(x + 5, y + 10, x + 15, y + 30, paint); // Левая ножка
        canvas.drawRect(x + 65, y + 10, x + 75, y + 30, paint); // Правая ножка
    }
}
