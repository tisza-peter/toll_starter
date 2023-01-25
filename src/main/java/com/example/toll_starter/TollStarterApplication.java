package com.example.toll_starter;
import com.example.toll_core.listvignettaforvehicle.ListOfVehicleWithVignettes;
import com.example.toll_core.listvignettaforvehicle.api.VehicleWithVignettesResponsePresenter;
import com.example.toll_core.listvignettaforvehicle.storage.VignettesStorage;
import com.example.toll_cui.TollSystemView;
import com.example.toll_cui.listvignettaforvehicle.Controller;
import com.example.toll_cui.listvignettaforvehicle.Presenter;
import com.example.toll_db_store.VignettesDbStoreApplication;
import com.example.vehicle_core.VehicleCoreApplication;
import com.example.vehicle_core.VehicleStorage;
import com.example.vehicle_filedirectory_store.Store_Repository_Implementation;

public class TollStarterApplication {

    public static void main(String[] args) {
        ListOfVehicleWithVignettes tollCore = new ListOfVehicleWithVignettes();

        TollSystemView view= new TollSystemView();

        Presenter presenterImpl = new Presenter();
        presenterImpl.setView(view);

        VehicleWithVignettesResponsePresenter vehicleWithVignettesResponsePresenter = presenterImpl;
        tollCore.setUIPresenter(vehicleWithVignettesResponsePresenter);

        VignettesStorage vignettesStorage = new VignettesDbStoreApplication();
        tollCore.SetStore(vignettesStorage);

        VehicleCoreApplication vehicleCore = new VehicleCoreApplication();
        VehicleStorage vehicleRepository = new Store_Repository_Implementation();
        vehicleCore.SetStore(vehicleRepository);

        tollCore.SetVehicleCoreInteractor(vehicleCore);
        vehicleCore.SetUI(tollCore);

        Controller controller = new Controller();
        controller.SetCore(tollCore);

        view.SetController(controller);
        view.Start();
    }
}
