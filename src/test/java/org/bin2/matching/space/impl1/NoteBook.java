package org.bin2.matching.space.impl1;

/**
 * Created by benoitroger on 19/01/15.
 */
public class NoteBook {

    public static enum HDDType {
        SSD, HYBRID, CLASSIC;
    }
    public static enum ScreenType {
        TN, IPS,ANTI_GLARE;
    }
    public static enum ProcessorType {
        I3, I5, I7, PENTIUM, CELERON
    }

    public static class Processor {
        private final int frequency;
        private final ProcessorType type;

        public Processor(int frequency, ProcessorType type) {
            this.frequency = frequency;
            this.type = type;
        }

        public int getFrequency() {
            return frequency;
        }

        public ProcessorType getType() {
            return type;
        }
    }


    private final int memory;
    private final int hddSpace;
    private final int price;
    private final HDDType hddType;
    private final ScreenType screenType;
    private final int screenSize;
    private final Processor processor;

    public NoteBook(int memory, int hddSpace, int price, HDDType hddType, ScreenType screenType, int screenSize, Processor processor) {
        this.memory = memory;
        this.hddSpace = hddSpace;
        this.price = price;
        this.hddType = hddType;
        this.screenType = screenType;
        this.screenSize = screenSize;
        this.processor = processor;
    }

    public int getMemory() {
        return memory;
    }

    public int getHddSpace() {
        return hddSpace;
    }

    public int getPrice() {
        return price;
    }

    public HDDType getHddType() {
        return hddType;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public Processor getProcessor() {
        return processor;
    }
}
