package moskalev.neoflexVacationCalculator.service;

import moskalev.neoflexVacationCalculator.feignClient.WeekendsAndWeekdaysFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

@Service
public class VacationServiceImpl implements VacationService {
    @Value("${months}")
    Integer months;
    @Value("${averagedays}")
    Double averageCalendarDays;//среднемесячное число календарных дней (29,3) (п. 10 Постановления 922):

    private final WeekendsAndWeekdaysFeignClient proxy;

    public VacationServiceImpl(WeekendsAndWeekdaysFeignClient proxy) {
        this.proxy = proxy;
    }

    @Override
    public String calculateMoney(int salary, int days) {
        double result = salary * months / 12 / averageCalendarDays * days;
        return String.format("%.2f", result); //округление до копеек
    }

    // если отпуск выпадает на праздничные дни или выходные, то они не входят и не подлежат оплате
    @Override
    public String calculateMoneyPro(int salary, String startday, String endDay) {

        Integer paidDays=0;
        String prefixDay=""; //префикс для запроса если месяц 9 то нужно чтобы был 09
        String prefixMonth=""; //префикс для дня если день 9 нужен чтобы был 09
        String[] startArray=startday.split("-");
        String[] endArray=endDay.split("-");
                                                                                            //-1 тк в Calendar январь это 00
        Calendar calendarStart=new GregorianCalendar(Integer.parseInt(startArray[0]),Integer.parseInt(startArray[1])-1,Integer.parseInt(startArray[2]));
        Calendar calendarEnd=new GregorianCalendar(Integer.parseInt(endArray[0]),Integer.parseInt(endArray[1])-1,Integer.parseInt(endArray[2]));
        while (calendarStart.before(calendarEnd)|| calendarStart.equals(calendarEnd)){ //пока текущая дата следует до тлт равна завершающей

            int month=calendarStart.get(Calendar.MONTH)+1; //тк у Calendar январь начинается с 00
            prefixDay=(calendarStart.get(Calendar.DAY_OF_MONTH)<10? "0" :"");
            prefixMonth= month<10? "0" :"";

            //формирование запроса на фейн клиент в формате "2022-10-26
            String request=calendarStart.get(Calendar.YEAR)+"-"+prefixMonth+month+"-"+ prefixDay+calendarStart.get(Calendar.DAY_OF_MONTH);

                if(proxy.whatIsDay(request).getBody().equals("0")){      //если вернуло 0-значит  будний день, если 1 значит выходной или праздник
                    paidDays++;
                }
                calendarStart.add(Calendar.DATE,1);//прибавить к текущей дате +1 день
        }
        return calculateMoney(salary,paidDays);
    }


}
