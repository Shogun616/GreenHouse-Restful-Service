package GreenHouse_WebService;

import java.util.List;

public interface IDataDao {
    
    public List<Sensordata> getAllData();
    public void persistData(List<Sensordata> dataList);
}
