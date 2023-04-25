package me.cedric.improvedtpr;

import com.earth2me.essentials.RandomTeleport;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.Math;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FindLoc {
    private Server server;
    private RandomTeleport tpr;
    int minRange = 1000; //Minimum distance between old and new location
    int maxRange = 2000; //Maximum distance between old and new location
    int minDistanceSqrd = 2500; //Minimum distance squared between the player using tpr and other players (using sqrt takes more computing power than necessary)
    Location NewLoc;
    int NumberOfPlayers;
    Player[] Names; //Array containing Players
    Location[] Locations; //Array containing player locations

    private Location convertToLoc(CompletableFuture<Location> future){
        Location NewLoc;
        try {
            NewLoc = future.get();
        } catch (ExecutionException e){
            e.getStackTrace();
            System.out.print("ExecutionException in getRandLoc");
            NewLoc = null;
        } catch (InterruptedException e){
            e.getStackTrace();
            System.out.print("InterruptedException in getRandLoc");
            NewLoc = null;
        } catch (CancellationException e){
            e.getStackTrace();
            System.out.print("CancellationException in getRandLoc");
            NewLoc = null;
        }
        return NewLoc;
    }
    public void getPlayersThisWorld(){
        String UserName = null;
        //Get a list of the players in this world:
        Collection UserListCol = server.getOnlinePlayers(); //Get player names
        Object[] UserList = UserListCol.toArray();

        //Get a list of locations for those players:
        this.NumberOfPlayers = UserList.length;
        for (int i = 0; i < this.NumberOfPlayers; i++){
            UserName = UserList[i].toString(); //User's name as string, in order to use in getPlayer()
            this.Names[i] = server.getPlayer(UserName);
            this.Locations[i] = server.getPlayer(UserName).getLocation();
        }
    }

    //Gets new random location that is at a minimum distance (defined through minDistanceSqrd) from other players:
    public Location getRandLoc(){
        FindLoc findLoc = new FindLoc(); //Initialize class

        findLoc.getPlayersThisWorld();

        Location center = tpr.getCenter();
        CompletableFuture<Location> future;
        future = tpr.getRandomLocation(center,minRange,maxRange);
        this.NewLoc = findLoc.convertToLoc(future);
        // Check if there are any other players near the new location:
        for (int i = 0; i < this.NumberOfPlayers; i++){
            double dist = Locations[i].distanceSquared(this.NewLoc);
            if (dist < minDistanceSqrd){
                future = tpr.getRandomLocation(center,minRange,maxRange);
                this.NewLoc = findLoc.convertToLoc(future);
            }
        }
        return this.NewLoc;
    }
}

