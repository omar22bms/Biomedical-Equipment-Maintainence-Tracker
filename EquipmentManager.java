import java.util.ArrayList;

public class EquipmentManager {
    private final ArrayList<Equipment> equipmentList = new ArrayList<>();

    public void addEquipment(Equipment e) {
        equipmentList.add(e);
    }

    public void removeEquipment(int index) {
        if (index >= 0 && index < equipmentList.size()) {
            equipmentList.remove(index);
        }
    }

    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }
}
