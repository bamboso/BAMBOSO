/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryManagment;

/**
 *
 * @author Olga Kryspin
 */
public class MemoryManagment {

    public ExchangeFile exchangeFile;

    private char ram[] = new char[128];
    private int pageLifeTime[] = new int[8];
    private String framesProcessesNames[] = new String[8];

    public MemoryManagment() {
        exchangeFile = new ExchangeFile();
        clearPageLifeTime();
        clearRAM();
    }

    private void addPageToFrame(int frameLRU, String process) {
        for (int i = 0; i < 16; i++) {
            ram[frameLRU * 16 + i] = process.charAt(i);
        }
    }

    private int getLRU() {
        int LRU = 0;
        int LRULifeTime = pageLifeTime[0];
        for (int i = 1; i < 8; i++) {
            if (pageLifeTime[i] < LRULifeTime) {
                LRULifeTime = pageLifeTime[i];
                LRU = i;
            }
        }
        changeLRU(LRU);
        return LRU;
    }

    public void addProcess(String processName, String program) {
        exchangeFile.setProcess(processName, program);
    }

    private void changeLRU(int framePosition) {
        int highestValue = pageLifeTime[0];
        for (int i = 1; i < 8; i++) {
            if (pageLifeTime[i] > highestValue) {
                highestValue = pageLifeTime[i];
            }
        }
        if (pageLifeTime[framePosition] == 0 || pageLifeTime[framePosition] != highestValue) {
            pageLifeTime[framePosition] = highestValue + 1;
        }
    }

    public char getCommandChar(int charPosition, String processName) {
        char commandChar = ' ';
        if (!exchangeFile.containsProcess(processName)) {
            return commandChar;
        }
        if (exchangeFile.getProcessSize(processName) > charPosition && charPosition >= 0 && exchangeFile.containsProcess(processName)) {
            int page;
            page = charPosition / 16;
            charPosition = charPosition % 16;
            int pagePositionInRAM = exchangeFile.isInRAM(processName, page);
            if (pagePositionInRAM == -1) {
                pagePositionInRAM = getLRU();
                framesProcessesNames[pagePositionInRAM] = processName;
                addPageToFrame(pagePositionInRAM, exchangeFile.getPage(processName, page));
                exchangeFile.updatePagesInformation(processName, pagePositionInRAM);
                exchangeFile.removeFromRAM(processName, pagePositionInRAM);
            }
            commandChar = ram[pagePositionInRAM * 16 + charPosition];
            changeLRU(pagePositionInRAM);
        }
        return commandChar;
    }

    private void clearRAM() {
        for (int i = 0; i < 128; i++) {
            ram[i] = ' ';
        }
    }

    private void clearPageLifeTime() {
        for (int i = 0; i < 8; i++) {
            pageLifeTime[i] = 0;
        }
    }

    public void writeToProcess(String processName, int position, char letter) {

    }

    public String getCurrentLRU() {
        String s = "";
        for (int i = 0; i < 8; i++) {
            s += pageLifeTime[i] + "  ";
        }
        return s;
    }

    public String getCurrentRAM() {
        String currentRAM = "";
        for (int i = 0; i < 128; i++) {
            currentRAM += ram[i];
        }
        return currentRAM;
    }

}