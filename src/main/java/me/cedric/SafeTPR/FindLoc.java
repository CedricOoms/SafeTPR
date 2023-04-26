package me.cedric.SafeTPR;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Collection;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class FindLoc {
    int minRange = 1000; //Minimum distance between old and new location
    int maxRange = 2000; //Maximum distance between old and new location
    int minDistance = 500;
    Location NewLoc;

    private boolean isValidLocation(Location loc){
        boolean isValid;
        if (!loc.isChunkLoaded()) {
            loc.getChunk().load();
        }
        Chunk chunk = loc.getChunk();
        Location GroundBlockLoc = loc.getBlock().getLocation(); //Block the player will be spawning (standing) on
        GroundBlockLoc.setY(GroundBlockLoc.getY()-1);
        Block block = GroundBlockLoc.getBlock();
        boolean ContainsWater = chunk.contains(Material.WATER.createBlockData());
        boolean ContainsLava = chunk.contains(Material.LAVA.createBlockData());
        isValid = !ContainsWater && !ContainsLava;
        return isValid;
    }

    private Location getRandomLocation(Location loc, int minRange, int maxRange, World world){
        int dx;
        int dz;
        double x = 0;
        double y = 0;
        double z = 0;
        boolean isNotValid = true;
        FindLoc findLoc = new FindLoc();
        Location NewLocation = new Location(world,x,y,z);
        //Loop until a valid location is found:
        while (isNotValid) {
            //Displacements:
            dx = ThreadLocalRandom.current().nextInt(minRange, maxRange + 1);
            dz = ThreadLocalRandom.current().nextInt(minRange, maxRange + 1);
            x = loc.getX() + dx;
            z = loc.getZ() + dz;
            NewLocation.set(x,0,z);
            isNotValid = !isValidLocation(NewLocation); //false => valid location found
        }
        y = NewLocation.getWorld().getHighestBlockYAt(NewLocation);
        NewLocation.setY(y+1);
        return NewLocation;
    }


    //Gets new random location that is at a minimum distance (defined through minDistanceSqrd) from other players:
    public Location getRandLoc(Player player,World world){
        FindLoc findLoc = new FindLoc(); //Initialize class
        this.NewLoc = findLoc.getRandomLocation(player.getLocation(),minRange,maxRange,world);
        // Check if there are any other players near the new location:
        int i = 0;
        while (i < world.getPlayerCount()){
            i++;
            Collection<Player> NearbyPlayers = world.getNearbyPlayers(this.NewLoc,minDistance); //Collection of players within radius of minDistance of the new location
            if ( !NearbyPlayers.isEmpty() ){
                System.out.print("Looking for random location...");
                //There are players nearby; find new location:
                this.NewLoc = findLoc.getRandomLocation(player.getLocation(),minRange,maxRange,world);
            }
            else {
                break;
            }
        }
        return this.NewLoc;
    }
}

