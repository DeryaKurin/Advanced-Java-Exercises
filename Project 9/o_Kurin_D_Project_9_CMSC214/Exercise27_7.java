/*
 * Class: CMSC214 
 * Instructor:
 * Description: A JavaFX application that animates MyHashMap as shown in Figure 27.8, page 1018. 
 * The initial size of the table can be changed. The load-factor threshold is 0.75.
 * Due: 11/8/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya O. Kurin
*/





package o_Kurin_D_Project_9_CMSC214;
	


import java.util.LinkedList;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Exercise27_7 extends Application {
	private MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
	Alert alert = new Alert(AlertType.INFORMATION);

	@Override
	public void start(Stage primaryStage) {
	
		try {
			
			final Canvas canvas = new Canvas(800,300);
			
			GraphicsContext g = canvas.getGraphicsContext2D();
			drawComponent(g);
			
			TextField tf1 = new TextField();
			TextField tf2 = new TextField("0.75");
			TextField tf3 = new TextField();
			
			Label lbl1 = new Label("Table Size: ");
			Label lbl2 =  new Label("Load factor: ");
			Label lbl3 = new Label("         Enter a value: ");
			
			Button newMap = new Button("New Map");
			Button insert = new Button("Insert");
			Button delete = new Button("Delete");
			Button search = new Button("Search");
			Button removeAll = new Button("Remove All");
	
			
			tf1.setPrefWidth(40);
			tf2.setPrefWidth(60);
			tf3.setPrefWidth(40);
			
			HBox hBox = new HBox();
			hBox.setSpacing(10);
			
			
			
			
			insert.setOnAction(e -> {
				try {
					int value = Integer.parseInt(tf3.getText());
				    if (map.containsKey(value)) {
				    	alert.setHeaderText("Information");
				    	alert.setContentText(tf3.getText() + " is already in the map");
				    	alert.showAndWait();
				    } else {
				    	map.put(value, value);
				    	g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				    	drawComponent(g);
				    	tf3.setText("");
				    }
				     	tf3.requestFocus();
				    } catch (NumberFormatException e2) {
				    	tf3.requestFocus();
				    }
			});
			
			delete.setOnAction(e -> {
				try {
					int value = Integer.parseInt(tf3.getText());
				    if (map.containsKey(value)) {
				    	map.remove(value);
				    	g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				    	drawComponent(g);
				    	tf3.setText("");
				    } else {
				    	alert.setHeaderText("Information");
				    	alert.setContentText(tf3.getText() + " is not in the map");
				    	alert.showAndWait();
				    }
				     	tf3.requestFocus();
				    } catch (NumberFormatException e2) {
				    	tf3.requestFocus();
				    }
			});
			
			
			search.setOnAction(e -> {
				try {
					int value = Integer.parseInt(tf3.getText());
				    if (map.containsKey(value)) {
				    	alert.setHeaderText("Information");
				    	alert.setContentText(tf3.getText() + " is in the map");
				    	alert.showAndWait();
				        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());	
				        drawComponent(g);
				    } else {
				    	alert.setHeaderText("Information");
				    	alert.setContentText(tf3.getText() + " is not in the map");
				    	alert.showAndWait();
				    }
				     	tf3.requestFocus();
				    } catch (NumberFormatException e2) {
				    	tf3.requestFocus();
				    }
			});
			
			
			removeAll.setOnAction(e -> {
				map.clear();
				g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				drawComponent(g);
				
			});
			
			
			newMap.setOnAction(e -> {
				try {
					 map.clear();
				     map = new MyHashMap<Integer, Integer>(Integer.parseInt(tf1.getText()), Float.parseFloat(tf2.getText()));
				     g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());	 
				     drawComponent(g);
				     } catch (NumberFormatException e2) {

				    }
			});
			
			
			
			hBox.getChildren().addAll(lbl1, tf1, lbl2, tf2, newMap, lbl3, tf3, insert, delete, search, removeAll);
			
			
			BorderPane root = new BorderPane();
			
			FlowPane fpane = new FlowPane(1000, 400);
			fpane.getChildren().add(canvas);
			fpane.getChildren().add(hBox);
			fpane.setVgap(10);			
			root.setCenter(fpane);
		
			Scene scene = new Scene(root, 1000, 400);
		
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Exercise 27_7 Project #9");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	protected void drawComponent(GraphicsContext g) {
		int size = 20;
		
		g.strokeText("Table size = " + map.table.length + "      Number of keys = " + map.size() + " ", size, size);
		g.strokeText("Load factor = " + (1.0 * map.size()) / map.table.length + "     Load factor threshold = " + map.getLoadFactorThreshold() + " ", size, size * 2);
		for (int i = 0; i < map.table.length; i++) {
			g.strokeText("[" + i + "]", size, size * (i + 4));
		    g.strokeRect(size * 3, size * (i + 3) + 6, size * 2, size);
		    
		    if (map.table[i] != null) {
		    	for (int j = 0; j < map.table[i].size(); j++) {
		    	g.strokeLine(size * (4 + 4* j), size * (i + 4) - 4, size * (6 + 4* j), size * (i + 4) - 4);
		        g.strokeLine(size * (6 + 4* j) - 10, size * (i + 4) - 4 + 5, size * (6 + 4* j), size * (i + 4) - 4);
		        g.strokeLine(size * (6 + 4* j) - 10, size * (i + 4) - 4 - 5, size * (6 + 4* j), size * (i + 4) - 4);
		        g.strokeRect(size * (6 + 4* j), size * (i + 3) + 6, size * 2, size);
		        g.strokeText("" + map.table[i].get(j).getValue(), size * (6 + 4* j) + 2, size * (i + 4) + 2);
		     }
		     
    	     g.setFill(Color.BLACK);
    	     
		    }
		}
	}
	


	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	static class MyHashMap<K, V> implements MyMap<K, V> {
		// Define the default hash table size. Must be a power of 2
		  private final static int DEFAULT_INITIAL_CAPACITY = 4;
		  
		  // Define the maximum hash table size. 1 << 30 is same as 2^30
		  private final static int MAXIMUM_CAPACITY = 1 << 30; 
		  
		  // Current hash table capacity. Capacity is a power of 2
		  private int capacity;
		  
		  // Define default load factor
		  private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 

		  // Specify a load factor used in the hash table
		  private float loadFactorThreshold; 
		     
		  // The number of entries in the map
		  private int size = 0; 
		  
		  // Hash table is an array with each cell that is a linked list
		  LinkedList<MyMap.Entry<K,V>>[] table;

		  /** Construct a map with the default capacity and load factor */
		  public MyHashMap() {  
		    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
		  }
		  
		  /** Construct a map with the specified initial capacity and 
		   * default load factor */
		  public MyHashMap(int initialCapacity) { 
		    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
		  }
		  
		  /** Construct a map with the specified initial capacity 
		   * and load factor */
		  @SuppressWarnings("unchecked")
		  public MyHashMap(int initialCapacity, float loadFactorThreshold) { 
			  if (initialCapacity > MAXIMUM_CAPACITY)
				  this.capacity = MAXIMUM_CAPACITY;
			  else
				  this.capacity = trimToPowerOf2(initialCapacity);
			    
			  this.setLoadFactorThreshold(loadFactorThreshold);    
			  table = new LinkedList[capacity];
		  }
		  
		  @Override /** Remove all of the entries from this map */ 
		  public void clear() {
			  size = 0;
			  removeEntries();
		  }

		  @Override /** Return true if the specified key is in the map */
		  public boolean containsKey(K key) {    
			  if (get(key) != null)
				  return true;
			  else
				  return false;
		  }
		  
		  @Override /** Return true if this map contains the value */ 
		  public boolean containsValue(V value) {
			  for (int i = 0; i < capacity; i++) {
				  if (table[i] != null) {
					  LinkedList<Entry<K, V>> bucket = table[i]; 
					  for (Entry<K, V> entry: bucket)
						  if (entry.getValue().equals(value)) 
							  return true;
				  }
			  }   
			  return false;
		  }
		  
		  @Override /** Return a set of entries in the map */
		  public java.util.Set<MyMap.Entry<K,V>> entrySet() {
			  java.util.Set<MyMap.Entry<K, V>> set = 
			  new java.util.HashSet<>();
			    
			  for (int i = 0; i < capacity; i++) {
				  if (table[i] != null) {
					  LinkedList<Entry<K, V>> bucket = table[i]; 
					  for (Entry<K, V> entry: bucket)
						  set.add(entry); 
				  }
			  }
			  return set;
		  }

		  @Override /** Return the value that matches the specified key */
		  public V get(K key) {
			  int bucketIndex = hash(key.hashCode());
			  if (table[bucketIndex] != null) {
				  LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 
				  for (Entry<K, V> entry: bucket)
					  if (entry.getKey().equals(key)) 
						  return entry.getValue();
			  } 
			  return null;
		  }
		  
		  @Override /** Return true if this map contains no entries */
		  public boolean isEmpty() {
			  return size == 0;
		  }  
		  
		  @Override /** Return a set consisting of the keys in this map */
		  public java.util.Set<K> keySet() {
			  java.util.Set<K> set = new java.util.HashSet<>();
			    
			  for (int i = 0; i < capacity; i++) {
				  if (table[i] != null) {
					  LinkedList<Entry<K, V>> bucket = table[i]; 
					  for (Entry<K, V> entry: bucket)
						  set.add(entry.getKey()); 
				  }
			  }
		    return set;
		  }
		      
		  @Override /** Add an entry (key, value) into the map */
		  public V put(K key, V value) {
			  if (get(key) != null) { // The key is already in the map
				  int bucketIndex = hash(key.hashCode());
				  LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 
				  for (Entry<K, V> entry: bucket)
					  if (entry.getKey().equals(key)) {
						  V oldValue = entry.getValue();
						  // Replace old value with new value
						  entry.value = value; 
						  // Return the old value for the key
						  return oldValue;
					  }
			  	}
			  
			  	// Check load factor
			  	if (size >= capacity * getLoadFactorThreshold()) {
			  		if (capacity == MAXIMUM_CAPACITY)
			  			throw new RuntimeException("Exceeding maximum capacity");
			      
			  		rehash();
			  	}
			    
			  	int bucketIndex = hash(key.hashCode());
			    
			 	// Create a linked list for the bucket if it is not created
			  	if (table[bucketIndex] == null) {
			  		table[bucketIndex] = new LinkedList<Entry<K, V>>();
			  	}
	
			  	// Add a new entry (key, value) to hashTable[index]
			  	table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));
			  	
			  	size++; // Increase size
			    
			  	return value;  
		  } 
		 
		  @Override /** Remove the entries for the specified key */
		  public void remove(K key) {
			  int bucketIndex = hash(key.hashCode());
			    
			  // Remove the first entry that matches the key from a bucket
			  if (table[bucketIndex] != null) {
				  LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 
				  for (Entry<K, V> entry: bucket)
					  if (entry.getKey().equals(key)) {
						  bucket.remove(entry);
						  size--; // Decrease size
						  break; // Remove just one entry that matches the key
					  }
			  	}
		  }
		  
		  @Override /** Return the number of entries in this map */
		  public int size() {
			  return size;
		  }
		  
		  @Override /** Return a set consisting of the values in this map */
		  public java.util.Set<V> values() {
			  java.util.Set<V> set = new java.util.HashSet<>();
		    
			  for (int i = 0; i < capacity; i++) {
			      if (table[i] != null) {
			    	  LinkedList<Entry<K, V>> bucket = table[i]; 
			    	  for (Entry<K, V> entry: bucket)
			    		  set.add(entry.getValue()); 
			      }
			  }
		    
			  return set;
		  }
		  
		  /** Hash function */
		  private int hash(int hashCode) {
			  return supplementalHash(hashCode) & (capacity - 1);
		  }
		  
		  /** Ensure the hashing is evenly distributed */
		  private static int supplementalHash(int h) {
			  h ^= (h >>> 20) ^ (h >>> 12);
			  return h ^ (h >>> 7) ^ (h >>> 4);
		  }

		  /** Return a power of 2 for initialCapacity */
		  private int trimToPowerOf2(int initialCapacity) {
			  int capacity = 1;
			  while (capacity < initialCapacity) {
				  capacity <<= 1;
			  }
		    
			  return capacity;
		  }
		  
		  /** Remove all entries from each bucket */
		  private void removeEntries() {
			  for (int i = 0; i < capacity; i++) {
				  if (table[i] != null) {
					  table[i].clear();
				  }
			  }
		  }
		  
		  /** Rehash the map */
		  private void rehash() {
			  java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
			  capacity <<= 1; // Double capacity    
			  table = new LinkedList[capacity]; // Create a new hash table
			  size = 0; // Reset size to 0
		    
			  for (Entry<K, V> entry: set) {
				  put(entry.getKey(), entry.getValue()); // Store to new table
			  }
		  }

		  @Override
		  public String toString() {
			  StringBuilder builder = new StringBuilder("[");
		    
			  for (int i = 0; i < capacity; i++) {
				  if (table[i] != null && table[i].size() > 0) 
					  for (Entry<K, V> entry: table[i])
						  builder.append(entry);
			  }
		    
			  builder.append("]");
			  return builder.toString();
		  }

		  public float getLoadFactorThreshold() {
			  return loadFactorThreshold;
		  }

		  public void setLoadFactorThreshold(float loadFactorThreshold) {
			  this.loadFactorThreshold = loadFactorThreshold;
		  
		  }
	}
	 

	interface MyMap<K, V> {
		/** Remove all of the entries from this map */
		public void clear();
	
		/** Return true if the specified key is in the map */
		public boolean containsKey(K key);
	
		/** Return true if this map contains the specified value */
		public boolean containsValue(V value);
	
		/** Return a set of entries in the map */
		public java.util.Set<Entry<K, V>> entrySet();
		
		/** Return the first value that matches the specified key */
		public V get(K key);
	
		/** Return true if this map contains no entries */
		public boolean isEmpty();
	
		/** Return a set consisting of the keys in this map */
		public java.util.Set<K> keySet();
	
		/** Add an entry (key, value) into the map */
		public V put(K key, V value);
	
		/** Remove the entries for the specified key */
		public void remove(K key);
	
		/** Return the number of mappings in this map */
		public int size();
	
		/** Return a set consisting of the values in this map */
		public java.util.Set<V> values();
	
		/** Define inner class for Entry */
		public static class Entry<K, V> {
			K key;
			V value;
	
			public Entry(K key, V value) {
				this.key = key;
				this.value = value;
			}
	
			public K getKey() {
				return key;
			}
	
			public V getValue() {
				return value;
			}
	
			@Override
			public String toString() {
				return "[" + key + ", " + value + "]";
			}
		}
	}
}
