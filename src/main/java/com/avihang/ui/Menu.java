package com.avihang.ui;

import com.avihang.handler.InputHandler;
import com.avihang.service.WebService;
import com.avihang.util.ColorSet;
import com.avihang.util.TextCleaner;

import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void showMainMenu() {
        while (true) {
            System.out.println(ColorSet.BLUE + "\n*** خوش آمدید ***");
            System.out.print("""
                    1) وارد کردن آدرس سایت
                    00) خروج
                    """);

            String input = sc.nextLine().trim();

            if (InputHandler.checkExit(input)) break;
            if (input.equals("1")) {
                handleWebsiteStage();
            } else {
                System.out.println("گزینه صحیح را وارد نمایید!");
            }
        }
    }

    private static void handleWebsiteStage() {
        while (true) {
            System.out.println(ColorSet.PURPLE + "آدرس سایت را وارد کنید (0 برای بازگشت، exit برای خروج): ");
            String urlInput = sc.nextLine().trim();

            if (InputHandler.checkBack(urlInput)) return;
            if (InputHandler.checkExit(urlInput)) System.exit(0);

            try {
                String html = WebService.getWebsiteHtml(urlInput);
                String cleanText = TextCleaner.clean(html);
                handleSearchStage(cleanText);
                break;
            } catch (Exception e) {
                System.out.println(ColorSet.RED + "آدرس معتبر نیست: " + e.getMessage());
            }
        }
    }

    private static void handleSearchStage(String text) {
        while (true) {
            System.out.println(ColorSet.YELLOW + "کلمه مورد نظر برای جستجو را وارد کنید (0 برای بازگشت، exit برای خروج): ");
            String word = sc.nextLine().trim();

            if (InputHandler.checkBack(word)) return;
            if (InputHandler.checkExit(word)) System.exit(0);

            int count = WebService.countWords(text, word);
            System.out.println(ColorSet.RED + "کلمه '" + word + "' به تعداد " + count + " بار تکرار شده است.");
            break;
        }
    }
}