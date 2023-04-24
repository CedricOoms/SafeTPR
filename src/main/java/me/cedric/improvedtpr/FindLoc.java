package me.cedric.improvedtpr;

import com.earth2me.essentials.RandomTeleport;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.Math;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class FindLoc {
    private RandomTeleport tpr;
    int minRange = 1000; //Minimum distance between old and new location
    int maxRange = 2000; //Maximum distance between old and new location
    int minDistance = 500; //Minimum distance between the player using tpr and other players
    private Location NewLocFin;
    int NumberOfPlayers;
    Object[][] NamesAndLocations; //2D array: first row contains list of Players, second row their Locations
    Player[] PlayerArray = NamesAndLocations[0];
    public void getPlayersThisWorld(Server server){
        NamesAndLocations[0][this.NumberOfPlayers] = new Player[this.NumberOfPlayers];
        String UserName = null;
        //Get a list of the players in this world:
        Collection UserListCol = server.getOnlinePlayers(); //Get player names
        Object[] UserList = UserListCol.toArray();

        //Get a list of locations for those players:
        this.NumberOfPlayers = UserList.length;
        for (int i = 0; i < this.NumberOfPlayers; i++){
            UserName = UserList[i].toString(); //User's name as string, in order to use in getPlayer()
            this.NamesAndLocations[0][i] = server.getPlayer(UserName);
            this.NamesAndLocations[1][i] = server.getPlayer(UserName).getLocation();
        }
    }

    public void getRandLoc(Location PlayerLoc){
        Location center = tpr.getCenter();
        CompletableFuture<Location> NewLoc = tpr.getRandomLocation(center,minRange,maxRange);
        // Check if there are any other players near the new location:
        for (int i = 0; i < NamesAndLocations[1].length; i++){
            double dist = NamesAndLocations[1][i].d
            if ()
        }
    }
}

