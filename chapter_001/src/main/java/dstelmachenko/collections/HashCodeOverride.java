package dstelmachenko.collections;

import java.util.GregorianCalendar;

/**
 *  5.5.9.1  Рассказать и продемонстрировать как переопределяют метод hashCode
 *
 * Переопределять метод hashCode() необходимо таким образом, чтобы у равных объектов метод возвращал бы одинаковый
 * результат, и, вместе с тем, чтобы для разных объектов возращались бы разные значения хэш-кода в максимальном
 * количестве случаев.
 *
 * Для этого необходимо вычислять хэш из всех значащих полей объекта. Хэш объекта вычисляется для каждого значащего
 * поля объекта по формуле:
 *
 *    hash = 31 * hash + fieldHash
 *
 *  ,где hash - текущее значение хэш-кода объекта
 *       fieldHash - хэш-код свойства объекта
 *       31 - происзвольны коэффициент, для макс. распределения хэшей лучше всего подходят простые нечетные числа.
 *
 * Значение каждого field-hash вычисляется разными способами в зависимости от типа поля.
 *
 *  - Короткие целочисленные типы (int, byte, char, short): берется значение.
 *  - Тип boolean: 1 или 0
 *  - Тип long: (int) ((f) ^ (f >>> 32)).
 *  - Тип float: Float.floatToIntBits(f).
 *  - Тип double приводим к типу long (Double.doubletoLongBits(f)) и далее для типа long (int) ((f) ^ (f >>> 32)).
 *  - Для ссылок на объект, в случае null возвращать 0, иначе вызывать hashCode объекта.
 *  - Для массивов вычислять каждый элемент, как отдельное поле.
 *
 *
 * Создадим класс с полями разных типов и попробуем в тестах получать и сравнить хэши его обектов
 */


public class HashCodeOverride {
    private String name;
    private String surname;
    private GregorianCalendar birthDate;
    private boolean sex;
    private char charSex;
    private double salary;
    private float monthlyFee;
    private long taxpayerNumber;
    private byte childrenQuantity;
    private Children[] children = new Children[50];

    public HashCodeOverride(String name, String surname, GregorianCalendar birthDate, boolean sex,
                            double salary, float monthlyFee, long taxpayerNumber) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.charSex = getCharSex(sex);
        this.salary = salary;
        this.monthlyFee = monthlyFee;
        this.taxpayerNumber = taxpayerNumber;
    }

    private char getCharSex(boolean sex) {
        return sex ? 'M' : 'F';
    }

    @Override
    public int hashCode() {
        int h = 1;
        h = 31 * h + (this.name != null ? this.name.hashCode() : 0);
        h = 31 * h + (this.surname != null ? this.surname.hashCode() : 0);
        h = 31 * h + (this.birthDate != null ? this.birthDate.hashCode() : 0);
        h = 31 * h + (this.sex ? 1 : 0);
        /**
         * charSex однозначно зависит от sex, в расчете хэша он не важен
         */
        Double salaryObj = salary;
        h = 31 * h + (salaryObj.hashCode());
        Float monthlyFeeObj = monthlyFee;
        h = 31 * h + (monthlyFeeObj.hashCode());
        h = 31 * h + (int) ((this.taxpayerNumber) ^ (this.taxpayerNumber >>> 32));
        /**
         * childrenQuantity однозначно зависит от количества элементов массива Дети, в расчете хэша он не важен
         */
        for (int i = 0; i < childrenQuantity; i++) {
            h = 31 * h + (children[i].hashCode());
        }
        return h;
    }

    public void addChild(String name, String surname, GregorianCalendar birthDate, boolean sex) {
        Children child = new Children(name, surname, birthDate, sex);
        children[childrenQuantity++] = child;
    }

    private class Children {
        private String name;
        private String surname;
        private GregorianCalendar birthDate;
        private boolean sex;
        private char charSex;

        public Children(String name, String surname, GregorianCalendar birthDate, boolean sex) {
            this.name = name;
            this.surname = surname;
            this.birthDate = birthDate;
            this.sex = sex;
            this.charSex = getCharSex(sex);
        }

        @Override
        public int hashCode() {
            int h = 1;
            h = 31 * h + (this.name != null ? this.name.hashCode() : 0);
            h = 31 * h + (this.surname != null ? this.surname.hashCode() : 0);
            h = 31 * h + (this.birthDate != null ? this.birthDate.hashCode() : 0);
            h = 31 * h + (this.sex ? 1 : 0);
            /**
             * charSex однозначно зависит от sex, в расчете хэша он не важен
             */
            return h;
        }

    }
}
