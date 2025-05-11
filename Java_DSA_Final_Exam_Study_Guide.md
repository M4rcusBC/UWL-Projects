# Java Final Exam Study Guide

## I. Lists

### A. Overview
A list is an ordered collection of elements, also known as a sequence. Elements can be accessed by their integer index, and lists may contain duplicate elements. In Java, `java.util.List` is the primary interface.

**Key Operations (common to most list implementations):**
*   `add(E element)`: Appends an element to the end.
*   `add(int index, E element)`: Inserts an element at a specific index.
*   `get(int index)`: Retrieves the element at a specific index.
*   `set(int index, E element)`: Replaces the element at a specific index.
*   `remove(int index)`: Removes the element at a specific index.
*   `remove(Object o)`: Removes the first occurrence of a specific element.
*   `size()`: Returns the number of elements.
*   `isEmpty()`: Checks if the list is empty.
*   `contains(Object o)`: Checks if the list contains a specific element.
*   `clear()`: Removes all elements.

### B. `java.util.ArrayList`

1.  **Definition & Properties:**
    *   Resizable array implementation of the `List` interface.
    *   Stores elements in a contiguous block of memory.
    *   Allows fast random access (get/set by index).
    *   Adding/removing elements can be slow if it requires shifting elements or resizing the array.
    *   Not synchronized by default.

2.  **Core Methods (Functionality & Explanation):**
    *   `add(E element)`: Adds element to the end. If internal array is full, a new, larger array is allocated, and existing elements are copied.
        *   *Explanation*: "This method appends the specified element to the end of this list. Internally, it checks if the backing array has enough capacity. If not, it increases the capacity (usually by 50% or 100% of the current size), copies the existing elements to the new array, and then adds the new element at the first empty spot."
    *   `add(int index, E element)`: Inserts element at `index`. Shifts subsequent elements to the right.
        *   *Explanation*: "This method inserts the specified element at the specified position in this list. It first checks if the index is valid. Then, it shifts any subsequent elements to the right (adds one to their indices) to make space. If the backing array is full, it's resized first. Finally, the new element is placed at the given index."
    *   `get(int index)`: Returns element at `index`. Direct array access.
        *   *Explanation*: "This method returns the element at the specified position in this list. Since `ArrayList` is backed by an array, this operation is very fast as it involves a direct lookup using the index."
    *   `remove(int index)`: Removes element at `index`. Shifts subsequent elements to the left.
        *   *Explanation*: "This method removes the element at the specified position in this list. It first checks if the index is valid. Then, it shifts any subsequent elements to the left (subtracts one from their indices) to fill the gap left by the removed element. The size of the list is decremented."

3.  **Implementation of Non-Trivial Methods (Conceptual):**
    *   **`add(int index, E element)` (when resizing is NOT needed):**
        ```java
        // Conceptual for ArrayList add(index, element)
        // Assume elementData is the backing array, size is current number of elements
        // Check index bounds (0 <= index <= size)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        // Ensure capacity (if elementData.length == size, resize first - not shown here)
        // Shift elements:
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
        ```
    *   **Resizing Logic (when `add` causes overflow):**
        Typically, a new array (e.g., 1.5x or 2x the old size) is created.
        Elements from the old array are copied to the new array.
        The new element is then added.

4.  **Time Complexity:**
    *   `add(E element)` (amortized): O(1)
    *   `add(int index, E element)`: O(n) (due to shifting)
    *   `get(int index)`: O(1)
    *   `set(int index, E element)`: O(1)
    *   `remove(int index)`: O(n) (due to shifting)
    *   `remove(Object o)`: O(n) (due to search)
    *   `contains(Object o)`: O(n)
    *   `size()`: O(1)

5.  **Use Cases:**
    *   When frequent random access (get/set) is needed.
    *   When the number of elements is relatively stable, or additions/removals mostly happen at the end.

### C. `java.util.LinkedList`

1.  **Definition & Properties:**
    *   Doubly-linked list implementation of the `List` and `Deque` interfaces.
    *   Each element (node) stores the data and pointers to the previous and next nodes.
    *   Efficient for insertions and deletions anywhere in the list (once the position is known).
    *   Slower random access (get/set by index) as it requires traversing the list.
    *   Not synchronized by default.

2.  **Core Methods (Functionality & Explanation):**
    *   `add(E element)` / `addLast(E e)`: Adds element to the end of the list.
        *   *Explanation*: "This method appends the specified element to the end of this list. It involves creating a new node, setting its 'previous' pointer to the current last node, updating the current last node's 'next' pointer to the new node, and finally, updating the list's 'last' reference to point to the new node. The list's size is incremented."
    *   `add(int index, E element)`: Inserts element at `index`. Involves finding the node at `index-1` and `index`, then adjusting pointers.
        *   *Explanation*: "This method inserts the specified element at the specified position. It first traverses the list to find the node currently at the given index (and the node before it). A new node is created. The new node's 'next' pointer is set to the node at the target index, and its 'previous' pointer to the node before the target index. The predecessor's 'next' and successor's 'previous' pointers are then updated to point to the new node."
    *   `get(int index)`: Returns element at `index`. Requires traversing from the beginning or end (whichever is closer).
        *   *Explanation*: "This method returns the element at the specified position. It internally optimizes by checking if the index is closer to the beginning or the end of the list. It then traverses from the closer end, following 'next' or 'previous' pointers until it reaches the desired node, and returns its data."
    *   `remove(int index)`: Removes element at `index`. Involves finding the node and adjusting its neighbors' pointers.
        *   *Explanation*: "This method removes the element at the specified position. It traverses to locate the node at the given index. Once found, it updates the 'next' pointer of the node's predecessor to point to the node's successor, and the 'previous' pointer of the node's successor to point to the node's predecessor, effectively 'unlinking' the node. The list's size is decremented."
    *   `addFirst(E e)`, `removeFirst()`, `addLast(E e)`, `removeLast()`: Efficient O(1) operations for deque functionality.

3.  **Implementation of Non-Trivial Methods (Conceptual for Doubly-Linked List):**
    *   **`add(int index, E element)`:**
        ```java
        // Conceptual for LinkedList add(index, element)
        // Node class: class Node { E data; Node prev, next; ... }
        // Assume head, tail, size are instance variables
        // Check index bounds (0 <= index <= size)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(...);
        }
        if (index == size) { // Add to end
            // ... (similar to addLast)
        } else {
            Node successor = nodeAtIndex(index); // Helper to find node at index
            Node predecessor = successor.prev;
            Node newNode = new Node<>(predecessor, element, successor);
            successor.prev = newNode;
            if (predecessor == null) { // Inserted at head
                head = newNode;
            } else {
                predecessor.next = newNode;
            }
            size++;
        }
        ```
    *   **`remove(int index)`:**
        ```java
        // Conceptual for LinkedList remove(index)
        Node nodeToRemove = nodeAtIndex(index); // Helper
        Node predecessor = nodeToRemove.prev;
        Node successor = nodeToRemove.next;

        if (predecessor == null) { // Removing head
            head = successor;
        } else {
            predecessor.next = successor;
            nodeToRemove.prev = null; // Help GC
        }

        if (successor == null) { // Removing tail
            tail = predecessor;
        } else {
            successor.prev = predecessor;
            nodeToRemove.next = null; // Help GC
        }
        // nodeToRemove.data = null; // Help GC
        size--;
        // return nodeToRemove.data;
        ```

4.  **Time Complexity:**
    *   `add(E element)` / `addLast(E e)`: O(1)
    *   `addFirst(E e)`: O(1)
    *   `add(int index, E element)`: O(n) (due to traversal to index)
    *   `get(int index)`: O(n)
    *   `set(int index, E element)`: O(n)
    *   `remove(int index)`: O(n) (traversal) but O(1) if node reference is already known (e.g. via Iterator)
    *   `remove(Object o)`: O(n) (search)
    *   `removeFirst()` / `removeLast()`: O(1)
    *   `contains(Object o)`: O(n)
    *   `size()`: O(1) (if size field is maintained)

5.  **Use Cases:**
    *   Frequent insertions/deletions in the middle of the list.
    *   When list is used as a queue or stack (due to efficient add/remove at ends).

### D. Comparing `ArrayList` vs. `LinkedList`

| Feature         | `ArrayList`                               | `LinkedList`                                |
|-----------------|-------------------------------------------|---------------------------------------------|
| **Internal Structure** | Dynamic Array                             | Doubly-Linked Nodes                         |
| **`get(index)`**  | O(1)                                      | O(n)                                        |
| **`add(E)` (end)** | O(1) amortized                            | O(1)                                        |
| **`add(index, E)`** | O(n)                                      | O(n) (traversal) + O(1) (insertion)       |
| **`remove(index)`** | O(n)                                      | O(n) (traversal) + O(1) (deletion)        |
| **Memory Usage**  | Generally less overhead per element       | More overhead per element (pointers)        |
| **Use When**    | Frequent random access, stable size       | Frequent insertions/deletions, queue/stack  |

## II. (Hash) Set & Map

### A. Overview
*   **`java.util.Set` Interface:** A collection that contains no duplicate elements. Models the mathematical set abstraction.
    *   Key Methods: `add()`, `remove()`, `contains()`, `size()`, `isEmpty()`.
*   **`java.util.Map` Interface:** An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.
    *   Key Methods: `put(K key, V value)`, `get(Object key)`, `remove(Object key)`, `containsKey(Object key)`, `containsValue(Object value)`, `size()`, `isEmpty()`, `keySet()`, `values()`, `entrySet()`.

### B. `java.util.HashSet`

1.  **Definition & Properties:**
    *   Implementation of the `Set` interface, backed by a `HashMap` instance.
    *   Does not guarantee the order of elements (iteration order can change).
    *   Permits the `null` element.
    *   Offers constant time performance for basic operations (`add`, `remove`, `contains`, `size`), assuming the hash function disperses elements properly.

2.  **Core Methods (Functionality & Explanation):**
    *   `add(E e)`: Adds the element if not already present.
        *   *Explanation*: "This method adds the specified element to this set if it is not already present. Internally, it uses a `HashMap` where the element `e` becomes the key, and a dummy `PRESENT` object is the value. If `map.put(e, PRESENT)` returns `null` (meaning `e` was not already a key), the element is successfully added, and the method returns `true`. Otherwise, it returns `false`."
    *   `remove(Object o)`: Removes the element if present.
        *   *Explanation*: "This method removes the specified element from this set if it is present. It delegates to the backing `HashMap`'s `remove(o)` method. It returns `true` if the set contained the element (and it was removed)."
    *   `contains(Object o)`: Returns `true` if the set contains the element.
        *   *Explanation*: "This method returns `true` if this set contains the specified element. It delegates to the backing `HashMap`'s `containsKey(o)` method."

3.  **How Hashing Works (Relies on `HashMap` principles):**
    *   **`hashCode()`:** Each object in Java has a `hashCode()` method. For `HashSet` (and `HashMap`), this method is crucial. It should return an integer representation of the object.
        *   *Contract*: If `a.equals(b)` is true, then `a.hashCode()` must be equal to `b.hashCode()`.
        *   *Desirable*: If `a.equals(b)` is false, `a.hashCode()` and `b.hashCode()` should ideally be different to minimize collisions.
    *   **`equals()`:** Used to check for actual equality if two objects have the same hash code (a "collision").
    *   **Collision Resolution:** If multiple elements hash to the same bucket (index in the internal array of the backing `HashMap`), they are typically stored in a linked list (or a balanced tree if the list gets too long, since Java 8) at that bucket.

4.  **Time Complexity (Average Case):**
    *   `add(E e)`: O(1)
    *   `remove(Object o)`: O(1)
    *   `contains(Object o)`: O(1)
    *   `size()`: O(1)
    *   *Worst Case (many collisions)*: O(n) for these operations.

5.  **Use Cases:**
    *   Storing unique items.
    *   Quickly checking for the existence of an item.
    *   Removing duplicates from a collection.

### C. `java.util.HashMap`

1.  **Definition & Properties:**
    *   Implementation of the `Map` interface based on a hash table.
    *   Stores key-value pairs.
    *   Does not guarantee the order of iteration.
    *   Permits one `null` key and multiple `null` values.
    *   Offers constant time performance for `get` and `put` operations on average.

2.  **Core Methods (Functionality & Explanation):**
    *   `put(K key, V value)`: Associates the specified value with the specified key.
        *   *Explanation*: "This method associates the specified value with the specified key in this map.
            1.  It calculates the hash code of the `key`.
            2.  This hash code is used to determine an index (bucket) in the internal array.
            3.  If the bucket is empty, a new entry (key-value pair) is stored there.
            4.  If the bucket is not empty (collision), it iterates through the entries in that bucket (which could be a linked list or a tree).
            5.  If an entry with an equal key is found, its value is updated with the new `value`, and the old value is returned.
            6.  If no such key is found in the bucket, the new key-value pair is added to this bucket (e.g., to the end of the list or as a new node in the tree).
            7.  If adding a new entry causes the number of entries to exceed a load factor threshold, the map is resized (rehashed)."
    *   `get(Object key)`: Returns the value to which the specified key is mapped, or `null` if the map contains no mapping for the key.
        *   *Explanation*: "This method returns the value associated with the specified key.
            1.  It calculates the hash code of the `key`.
            2.  This hash code is used to determine the bucket index.
            3.  It then searches within that bucket (iterating through the list/tree of entries if there are collisions) for an entry whose key `equals()` the specified `key`.
            4.  If found, the associated value is returned. Otherwise, `null` is returned."
    *   `remove(Object key)`: Removes the mapping for a key if it is present.
        *   *Explanation*: Similar to `get`, it finds the entry and then removes it from the bucket structure, returning the previous value or `null`.
    *   `containsKey(Object key)`: Returns `true` if this map contains a mapping for the specified key.
        *   *Explanation*: Similar to `get`, but returns `true` if the key is found, `false` otherwise.

3.  **Implementation of Non-Trivial Methods (Conceptual):**
    *   **`put(K key, V value)`:**
        ```java
        // Conceptual for HashMap put(key, value)
        // Node<K,V>[] table; // Array of buckets (e.g., LinkedList<Node> or TreeNode)
        // int hash = hash(key); // Calculate hash
        // int index = (table.length - 1) & hash; // Determine bucket index

        // If table[index] is null, create new Node and put it there.
        // Else (collision):
        //   Iterate through nodes in table[index]:
        //     If node.key.equals(key), update node.value = value, return oldValue.
        //   If key not found, add new Node(key, value) to this bucket's list/tree.
        //   (If list becomes too long, convert to tree - Java 8+)

        // Increment size.
        // If (size > loadFactor * table.length), resize (rehash) the table.
        // Return null (if new key) or oldValue.
        ```
    *   **`get(Object key)`:**
        ```java
        // Conceptual for HashMap get(key)
        // int hash = hash(key);
        // int index = (table.length - 1) & hash;

        // If table[index] is not null:
        //   Iterate through nodes in table[index]:
        //     If node.hash == hash && (node.key == key || (key != null && key.equals(node.key))):
        //       Return node.value;
        // Return null;
        ```
    *   **Resizing (Rehashing):** When the map becomes too full (exceeds `loadFactor * capacity`), a new table (usually double the size) is created. All existing entries are re-hashed and placed into their new correct buckets in the new table. This is an O(n) operation where n is the number of entries.

4.  **Time Complexity (Average Case):**
    *   `put(K key, V value)`: O(1)
    *   `get(Object key)`: O(1)
    *   `remove(Object key)`: O(1)
    *   `containsKey(Object key)`: O(1)
    *   `size()`: O(1)
    *   *Worst Case (many collisions)*: O(n) for these operations.

5.  **Use Cases:**
    *   Associating data (values) with unique identifiers (keys).
    *   Fast lookups, insertions, and deletions of key-value pairs.
    *   Implementing caches, symbol tables, frequency counters.

## III. Binary Search Tree (BST)

### A. Definition & Properties
A Binary Search Tree is a node-based binary tree data structure which has the following properties:
*   The left subtree of a node contains only nodes with keys lesser than the node's key.
*   The right subtree of a node contains only nodes with keys greater than the node's key.
*   The left and right subtree each must also be a binary search tree.
*   There must be no duplicate keys (in a standard BST, or duplicates handled consistently, e.g., in the right subtree).

**Node Structure (Conceptual):**
```java
class Node {
    int key;
    // V value; // If storing key-value pairs
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}
```

### B. Core Operations

1.  **Search (Find):**
    *   *Functionality*: To find a node with a given key.
    *   *Explanation*: "Start at the root. If the tree is empty, the key is not found. Compare the target key with the current node's key. If they match, the key is found. If the target key is less than the current node's key, search in the left subtree. If the target key is greater, search in the right subtree. Repeat this process recursively until the key is found or a null subtree is reached (key not found)."
    *   *Implementation (Recursive Pseudocode):*
        ```
        SEARCH(node, key_to_find):
          IF node is NULL OR node.key == key_to_find:
            RETURN node
          IF key_to_find < node.key:
            RETURN SEARCH(node.left, key_to_find)
          ELSE:
            RETURN SEARCH(node.right, key_to_find)
        ```

2.  **Insert:**
    *   *Functionality*: To add a new key to the BST while maintaining its properties.
    *   *Explanation*: "Start at the root. If the tree is empty, the new key becomes the root. Otherwise, compare the new key with the current node's key. If the new key is less, move to the left child; if greater, move to the right child. Repeat this until a null child pointer is reached. Insert the new node at this null position. If the key already exists, you might update its value or ignore the insertion, depending on the policy."
    *   *Implementation (Recursive Pseudocode):*
        ```
        INSERT(node, key_to_insert):
          IF node is NULL:
            RETURN new Node(key_to_insert)
          
          IF key_to_insert < node.key:
            node.left = INSERT(node.left, key_to_insert)
          ELSE IF key_to_insert > node.key:
            node.right = INSERT(node.right, key_to_insert)
          // ELSE: key already exists, handle as per requirement (e.g., update value or do nothing)
          
          RETURN node // Return the (possibly modified) node pointer
        ```

3.  **Delete:**
    *   *Functionality*: To remove a node with a given key while maintaining BST properties. This is the most complex operation.
    *   *Explanation*: "First, find the node to delete.
        1.  **Node is a leaf (no children):** Simply remove it (set parent's child pointer to null).
        2.  **Node has one child:** Replace the node with its child.
        3.  **Node has two children:** Find its in-order successor (the smallest key in its right subtree) or in-order predecessor (largest key in its left subtree). Copy the successor's/predecessor's key to the node to be deleted. Then, recursively delete the successor/predecessor (which will have at most one child)."
    *   *Implementation (Conceptual for case 3 - using in-order successor):*
        ```java
        // Conceptual for BST delete(root, key)
        // ... (find nodeToDelete) ...
        // If nodeToDelete has two children:
        Node successor = findMin(nodeToDelete.right); // Helper: finds min key in a subtree
        nodeToDelete.key = successor.key; // Copy successor's key
        // nodeToDelete.value = successor.value; // If storing values
        nodeToDelete.right = delete(nodeToDelete.right, successor.key); // Delete the successor
        ```
        *   `findMin(node)`: Keep going left from `node` until `node.left` is null. That `node` is the minimum.

4.  **Traversal:**
    *   **In-order (LNR - Left, Node, Right):** Visits nodes in ascending order of keys.
        *   *Explanation*: "Recursively traverse the left subtree, then visit the current node (e.g., print its key), then recursively traverse the right subtree."
        *   *Pseudocode*:
            ```
            IN_ORDER_TRAVERSAL(node):
              IF node is NOT NULL:
                IN_ORDER_TRAVERSAL(node.left)
                VISIT(node) // e.g., print node.key
                IN_ORDER_TRAVERSAL(node.right)
            ```
    *   **Pre-order (NLR - Node, Left, Right):** Visits the root before its children. Useful for copying trees.
        *   *Pseudocode*:
            ```
            PRE_ORDER_TRAVERSAL(node):
              IF node is NOT NULL:
                VISIT(node)
                PRE_ORDER_TRAVERSAL(node.left)
                PRE_ORDER_TRAVERSAL(node.right)
            ```
    *   **Post-order (LRN - Left, Right, Node):** Visits the root after its children. Useful for deleting trees (freeing memory).
        *   *Pseudocode*:
            ```
            POST_ORDER_TRAVERSAL(node):
              IF node is NOT NULL:
                POST_ORDER_TRAVERSAL(node.left)
                POST_ORDER_TRAVERSAL(node.right)
                VISIT(node)
            ```

### C. Implementation Details
*   Typically implemented using a `Node` class with `key`, `left` child, and `right` child references.
*   Operations are often implemented recursively due to the recursive nature of the tree structure, but iterative versions are also possible.

### D. Time Complexity
For a BST with `n` nodes:
*   **Search, Insert, Delete:**
    *   Average Case (balanced tree): O(log n)
    *   Worst Case (skewed tree, e.g., like a linked list): O(n)
*   **Traversal:** O(n) (visits each node once)

### E. Balancing
Unbalanced BSTs can degrade performance to O(n). Self-balancing BSTs (like Red-Black Trees, AVL Trees) maintain a balanced structure to ensure O(log n) performance for major operations.

### F. Use Cases
*   Implementing `Map` and `Set` data structures where ordered iteration is required (e.g., `java.util.TreeMap`, `java.util.TreeSet` are based on Red-Black Trees).
*   Symbol tables.
*   Indexing in databases (though B-Trees/B+ Trees are more common for disk-based storage).

## IV. Red-Black Tree

### A. Definition & Properties
A Red-Black Tree is a self-balancing Binary Search Tree where each node has an extra bit for storing color ("red" or "black"). By constraining the way nodes can be colored on any path from the root to a leaf, Red-Black trees ensure that no path is more than twice as long as any other, so the tree remains approximately balanced.

### B. Rules/Constraints
1.  Every node is either red or black.
2.  The root is always black.
3.  All leaves (NIL nodes, typically conceptual/sentinel nodes) are black.
4.  If a node is red, then both its children must be black. (No two red nodes in a row on a path).
5.  Every simple path from a given node to any of its descendant NIL leaves contains the same number of black nodes (this is called the "black-height").

These rules ensure that the longest path from root to leaf is no more than twice the length of the shortest path.

### C. Operations
Operations like search are the same as in a BST. Insert and delete are more complex because they must maintain the Red-Black properties.

1.  **Insert:**
    *   *Functionality*: Perform a standard BST insertion, color the new node red. Then, fix any Red-Black property violations that arise.
    *   *Explanation*: "First, insert the new node as you would in a BST and color it RED. This might violate rule 2 (if it's the root) or rule 4 (if its parent is also red).
        *   If the new node is the root, color it BLACK.
        *   If the parent of the new node is BLACK, no properties are violated.
        *   If the parent is RED (a "red-red" violation), we need to perform rebalancing:
            *   **Case 1 (Uncle is RED):** Recolor parent, grandparent, and uncle. Move pointer to grandparent and repeat.
            *   **Case 2 (Uncle is BLACK, and new node is an "inner" grandchild - forms a triangle):** Perform a rotation on the parent to make it an "outer" grandchild. This transforms it to Case 3.
            *   **Case 3 (Uncle is BLACK, and new node is an "outer" grandchild - forms a line):** Perform a rotation on the grandparent and recolor.
        This process of rotations and recolorings propagates up the tree if necessary."

2.  **Delete:**
    *   *Functionality*: Perform a standard BST deletion. If the deleted node (or the node that replaced it) was black, it might violate Red-Black properties (specifically rule 5, black-height).
    *   *Explanation*: "Deletion is more complex. After the standard BST deletion (which might involve replacing the node with its successor), if a BLACK node was removed or moved, the black-height property might be violated. This creates a "double black" situation at the node that replaced the deleted black node (or at its child if the deleted node was black and had a red child).
        Fixing this involves considering several cases based on the color of the sibling and its children, using rotations and recolorings to restore the black-height property and other rules. This is generally the most complex operation in a Red-Black tree."

**Rotations:**
*   **Left Rotation (on node `x`):** `x`'s right child `y` becomes `x`'s parent. `x` becomes `y`'s left child. `y`'s original left child becomes `x`'s right child.
*   **Right Rotation (on node `y`):** `y`'s left child `x` becomes `y`'s parent. `y` becomes `x`'s right child. `x`'s original right child becomes `y`'s left child.
Rotations preserve the BST property.

### D. Comparison with BST
*   **Balance:** Red-Black trees are always approximately balanced, guaranteeing O(log n) worst-case time. BSTs can be unbalanced (O(n) worst-case).
*   **Complexity:** Red-Black trees are more complex to implement due to balancing rules.
*   **Performance:** For average cases, BST might be slightly faster due to less overhead if the data is random. Red-Black trees provide better worst-case guarantees.

### E. Time Complexity
For a Red-Black Tree with `n` nodes:
*   Search, Insert, Delete: O(log n) (worst-case)
*   Rotations and recolorings take O(1) time, and at most O(log n) such operations are needed for insert/delete.

### F. Use Cases
*   `java.util.TreeMap` and `java.util.TreeSet` in Java are implemented using Red-Black trees.
*   Many operating system schedulers (e.g., Linux CFS).
*   Implementing associative arrays where ordered iteration and guaranteed performance are needed.

## V. B+ Tree

### A. Definition & Properties
A B+ Tree is a self-balancing tree data structure that maintains sorted data and allows for efficient insertion, deletion, and search operations, typically in logarithmic time. It's optimized for systems that read and write large blocks of data, making it ideal for databases and file systems.

**Key Properties:**
1.  All leaf nodes are at the same level.
2.  Leaf nodes are linked together (like a linked list) to allow for efficient sequential traversal.
3.  Internal nodes (non-leaf nodes) store only keys (search keys) and pointers to child nodes. These keys guide the search.
4.  Data records (or pointers to data records) are stored *only* in the leaf nodes.
5.  Each node (except possibly the root) must be at least half-full of keys/pointers.
6.  The root has at least two children if it's not a leaf node.
7.  A node can have a maximum of `m` children (where `m` is the order of the B+ tree) and `m-1` keys.

### B. Structure
*   **Order `m`**: Maximum number of children a node can have.
*   **Internal Nodes**: Contain up to `m-1` keys and `m` child pointers. The keys in an internal node act as separators for the keys in its children. For a key `K_i` in an internal node, the `i`-th child pointer points to a subtree where all keys are less than `K_i`, and the `(i+1)`-th child pointer points to a subtree where all keys are greater than or equal to `K_i`. (Conventions can vary slightly).
*   **Leaf Nodes**: Contain data entries (or pointers to them) and up to `m-1` keys. They are linked sequentially to their next sibling leaf node.

### C. Operations

1.  **Search (Find key `K`):**
    *   *Functionality*: Locate the leaf node that should contain key `K`.
    *   *Explanation*: "Start at the root. At each internal node, use the keys to determine which child pointer to follow. If `K` is less than the first key, follow the first pointer. If `K` is between key `K_i` and `K_{i+1}`, follow the pointer after `K_i`. If `K` is greater than the last key, follow the last pointer. Repeat until a leaf node is reached. Then, search for `K` within that leaf node."
    *   *Implementation (Conceptual):*
        ```
        SEARCH(node, K):
          IF node is a LEAF:
            Search for K in node.keys
            IF found, RETURN data associated with K
            ELSE RETURN NOT_FOUND
          ELSE (node is INTERNAL):
            Find child_pointer C based on K and node.keys
            RETURN SEARCH(child_node_pointed_by_C, K)
        ```

2.  **Insert (key `K`, value `V`):**
    *   *Functionality*: Add a new key-value pair.
    *   *Explanation*:
        1.  "Find the appropriate leaf node `L` where `K` should be inserted (using the search logic).
        2.  Insert `K` (and `V`) into `L` in sorted order.
        3.  **If `L` has space (is not full):** Done.
        4.  **If `L` is full (overflows):**
            a.  Split `L` into two leaf nodes, `L` and `L'`. Distribute roughly half the entries to `L` and half to `L'`.
            b.  Take the smallest key from `L'` (let's call it `K'`) and insert it into the parent of `L`. The pointer associated with `K'` in the parent will point to `L'`.
            c.  If the parent node also overflows due to this insertion, split the parent (an internal node) similarly. This splitting can propagate up to the root. If the root splits, a new root is created, and the tree height increases by one."
    *   *Splitting an Internal Node*: When an internal node `N` splits into `N` and `N'`, the middle key is pushed up to `N`'s parent, and `N'` becomes a new child of the parent.

3.  **Delete (key `K`):**
    *   *Functionality*: Remove a key-value pair.
    *   *Explanation*:
        1.  "Find the leaf node `L` containing `K` (using search logic).
        2.  Remove `K` (and its value) from `L`.
        3.  **If `L` is still at least half-full:** Done (you might need to update a key in an ancestor internal node if `K` was also used as a separator key and was the smallest in `L`).
        4.  **If `L` is underflow (less than half-full):**
            a.  **Try Redistribution (Borrowing):** If an adjacent sibling leaf node has more than the minimum number of entries, borrow an entry from it. This involves moving a key/value from the sibling to `L` and updating the parent node's separator key.
            b.  **Try Merging:** If redistribution is not possible (siblings are also at minimum capacity), merge `L` with an adjacent sibling. This involves moving all entries from one node to the other and deleting the now-empty node. The separator key in the parent corresponding to the merged nodes must also be removed from the parent.
            c.  If merging or removing a key from the parent causes the parent to underflow, this underflow condition propagates up the tree, potentially leading to merges of internal nodes or a decrease in tree height if the root's children are merged."

### D. Comparison with BST and B-Tree
*   **vs. BST:** B+ Trees are much wider and shallower, meaning fewer disk I/Os for large datasets. BSTs are better for in-memory where node access is cheap.
*   **vs. B-Tree:**
    *   In B-Trees, data pointers can be stored in internal nodes as well as leaf nodes. In B+ Trees, data pointers are *only* in leaf nodes.
    *   This makes B+ Tree leaf nodes form a dense index (all data is there), and internal nodes form a sparse index.
    *   Sequential access is faster in B+ Trees due to linked leaf nodes.
    *   B+ Trees might have slightly higher internal nodes because they only store keys, potentially leading to a wider tree and thus even shallower.

### E. Time Complexity (Order `m`, `n` total entries)
*   Search, Insert, Delete: O(log_m n) (This is effectively O(log n) but the base `m` is important for disk I/O analysis, as larger `m` means shallower tree).
*   Range Queries / Sequential Scan (after first lookup): Very efficient due to linked leaf nodes.

### F. Use Cases
*   **Database Management Systems (DBMS):** For indexing tables (e.g., MySQL InnoDB, PostgreSQL).
*   **File Systems:** For directory structures and file indexing (e.g., NTFS, HFS+).

## VI. Priority Queue (Heap Implementation)

### A. Definition & Properties
A Priority Queue is an abstract data type similar to a regular queue or stack, but where each element additionally has a "priority" associated with it. An element with high priority is served before an element with low priority. If two elements have the same priority, they are typically served according to their order in the queue.

*   **Min-Priority Queue:** `poll()` or `remove()` returns the element with the smallest priority.
*   **Max-Priority Queue:** `poll()` or `remove()` returns the element with the largest priority.

Java's `java.util.PriorityQueue` is by default a Min-Priority Queue.

### B. Heap Data Structure
A common and efficient way to implement a priority queue is using a heap. A **binary heap** is a complete binary tree that satisfies the heap property:
*   **Min-Heap Property:** The key of each node is less than or equal to the keys of its children. Thus, the root contains the minimum element.
*   **Max-Heap Property:** The key of each node is greater than or equal to the keys of its children. Thus, the root contains the maximum element.

1.  **Array Representation:**
    A heap (which is a complete binary tree) can be efficiently represented using an array:
    *   Root is at index 0 (or 1, depending on convention).
    *   For a node at index `i`:
        *   Left child: `2*i + 1` (if 0-indexed) or `2*i` (if 1-indexed)
        *   Right child: `2*i + 2` (if 0-indexed) or `2*i + 1` (if 1-indexed)
        *   Parent: `(i-1) / 2` (integer division, if 0-indexed) or `i / 2` (if 1-indexed)

2.  **Heap Property:** As defined above (Min-Heap or Max-Heap).

### C. Core Operations (Assuming Min-Heap)

1.  **Insert (add, offer):**
    *   *Functionality*: Adds an element to the priority queue.
    *   *Explanation*: "Add the new element to the end of the array (first available spot in the complete binary tree). This might violate the heap property. To restore it, perform a **heapify-up** (or sift-up/percolate-up) operation: compare the newly added element with its parent. If it's smaller than its parent (for a min-heap), swap them. Repeat this process with the element and its new parent until it's in the correct position (either it's greater than or equal to its parent, or it becomes the root)."
    *   *Implementation (Conceptual `heapifyUp` for Min-Heap, 0-indexed array `heap` of size `currentSize`):*
        ```
        ADD_ELEMENT(element):
          heap[currentSize] = element
          heapifyUp(currentSize)
          currentSize++

        HEAPIFY_UP(index):
          parentIndex = (index - 1) / 2
          WHILE index > 0 AND heap[index] < heap[parentIndex]:
            SWAP(heap[index], heap[parentIndex])
            index = parentIndex
            parentIndex = (index - 1) / 2
        ```

2.  **Delete Min (remove, poll):**
    *   *Functionality*: Removes and returns the element with the minimum priority (the root).
    *   *Explanation*: "The minimum element is at the root. To remove it:
        1.  Save the root element (to be returned).
        2.  Replace the root element with the last element in the heap (the rightmost leaf in the last level).
        3.  Decrement the heap size.
        4.  The new root might violate the heap property. Perform a **heapify-down** (or sift-down/percolate-down) operation: compare the new root with its children. If it's larger than one or both of its children (for a min-heap), swap it with the smaller of its children. Repeat this process with the element and its new children until it's in the correct position (either it's smaller than or equal to both its children, or it becomes a leaf node)."
    *   *Implementation (Conceptual `heapifyDown` for Min-Heap, 0-indexed array `heap` of size `currentSize`):*
        ```
        POLL_MIN():
          IF currentSize == 0: THROW EmptyQueueException
          minElement = heap[0]
          heap[0] = heap[currentSize - 1]
          currentSize--
          heapifyDown(0)
          RETURN minElement

        HEAPIFY_DOWN(index):
          smallest = index
          left = 2 * index + 1
          right = 2 * index + 2

          IF left < currentSize AND heap[left] < heap[smallest]:
            smallest = left
          IF right < currentSize AND heap[right] < heap[smallest]:
            smallest = right
          
          IF smallest != index: // If current node is not the smallest
            SWAP(heap[index], heap[smallest])
            heapifyDown(smallest) // Recursively heapify down the affected subtree
        ```

3.  **Peek (element, peek):**
    *   *Functionality*: Returns the element with the minimum priority without removing it.
    *   *Explanation*: "Simply return the element at the root of the heap (index 0)."
    *   Time Complexity: O(1)

### D. Heapify (Building a heap from an unsorted array)
*   *Functionality*: Convert an arbitrary array into a valid heap.
*   *Explanation*: "Start from the last non-leaf node (index `(n/2) - 1` for 0-indexed array of size `n`) and go up to the root. For each of these nodes, perform `heapifyDown`. This ensures that all subtrees rooted at these nodes become valid heaps."
*   Time Complexity: O(n) (surprisingly, not O(n log n) due to tighter analysis)

### E. Implementation Details
*   Java's `java.util.PriorityQueue` uses a binary min-heap.
*   Elements must be comparable (implement `Comparable`) or a `Comparator` must be provided.

### F. Time Complexity (for heap-based Priority Queue with `n` elements)
*   `add` / `offer` (Insert): O(log n)
*   `remove` / `poll` (Delete Min/Max): O(log n)
*   `peek` / `element` (Get Min/Max): O(1)
*   `size`: O(1)
*   Building a heap from `n` elements (Heapify): O(n)

### G. Use Cases
*   Dijkstra's algorithm for shortest paths.
*   Prim's algorithm for Minimum Spanning Tree.
*   Huffman coding (for building frequency trees).
*   Event-driven simulation schedulers.
*   Task scheduling in operating systems.
*   Any scenario requiring efficient retrieval of the "most important" item.

## VII. Graph Algorithms (Pseudocode)

### A. Basic Graph Terminology
*   **Vertex (Node):** A fundamental part of a graph.
*   **Edge (Link):** Connects two vertices.
*   **Directed Graph:** Edges have a direction (A -> B is different from B -> A).
*   **Undirected Graph:** Edges have no direction (A - B is the same as B - A).
*   **Weighted Graph:** Edges have associated weights or costs.
*   **Unweighted Graph:** Edges have no weights (or all weights are considered 1).
*   **Adjacency List:** For each vertex, store a list of its adjacent vertices. Efficient for sparse graphs.
*   **Adjacency Matrix:** A 2D matrix where `matrix[i][j] = 1` (or weight) if there's an edge from vertex `i` to `j`, else 0 (or infinity). Efficient for dense graphs.

### B. Topological Sort

1.  **Concept & Use Cases:**
    *   A linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge `u -> v`, vertex `u` comes before vertex `v` in the ordering.
    *   If the graph has a cycle, it has no topological sort.
    *   *Use Cases*: Task scheduling (dependencies), course prerequisites, resolving symbol dependencies in compilers.

2.  **Algorithm (Kahn's Algorithm - using in-degrees):**
    *   *Explanation*: "Maintain the in-degree (number of incoming edges) for each vertex.
        1.  Compute in-degrees for all vertices.
        2.  Initialize a queue with all vertices having an in-degree of 0.
        3.  Initialize an empty list for the topological sort result.
        4.  While the queue is not empty:
            a.  Dequeue a vertex `u`. Add `u` to the result list.
            b.  For each neighbor `v` of `u`:
                i.  Decrement in-degree of `v`.
                ii. If in-degree of `v` becomes 0, enqueue `v`.
        5.  If the count of visited vertices in the result list is not equal to the total number of vertices in the graph, then the graph has a cycle (and no topological sort is possible)."

3.  **Pseudocode (Kahn's Algorithm):**
    ```pseudocode
    TOPOLOGICAL_SORT(Graph G):
      in_degree = array of size G.num_vertices, initialized to 0
      FOR each vertex u in G:
        FOR each neighbor v of u: // (u -> v)
          in_degree[v]++
      
      queue = new Queue()
      FOR each vertex u in G:
        IF in_degree[u] == 0:
          queue.enqueue(u)
          
      topological_order = new List()
      visited_count = 0
      
      WHILE queue is not empty:
        u = queue.dequeue()
        topological_order.add(u)
        visited_count++
        
        FOR each neighbor v of u: // (u -> v)
          in_degree[v]--
          IF in_degree[v] == 0:
            queue.enqueue(v)
            
      IF visited_count != G.num_vertices:
        RETURN "Graph has a cycle, no topological sort"
      ELSE:
        RETURN topological_order
    ```

4.  **Time Complexity:** O(V + E) where V is the number of vertices and E is the number of edges (due to calculating in-degrees and visiting each edge once).

### C. Single-Source Shortest Path (Unweighted Graph)

1.  **Concept & Use Cases:**
    *   Finds the shortest path (in terms of number of edges) from a given source vertex `s` to all other reachable vertices in an unweighted graph.
    *   *Use Cases*: "Fewest hops" in a network, shortest path in a maze, level order traversal in trees (a special case of graphs).

2.  **Algorithm (Breadth-First Search - BFS):**
    *   *Explanation*: "BFS explores the graph layer by layer.
        1.  Start at the source vertex `s`. Mark it as visited and add it to a queue. Set its distance to 0.
        2.  While the queue is not empty:
            a.  Dequeue a vertex `u`.
            b.  For each unvisited neighbor `v` of `u`:
                i.  Mark `v` as visited.
                ii. Set distance to `v` as `distance to u + 1`.
                iii. Set predecessor of `v` as `u` (to reconstruct path).
                iv. Enqueue `v`."

3.  **Pseudocode (BFS for SSSP Unweighted):**
    ```pseudocode
    BFS_SSSP_UNWEIGHTED(Graph G, Vertex source):
      distance = array of size G.num_vertices, initialized to INFINITY
      predecessor = array of size G.num_vertices, initialized to NULL
      visited = set or boolean array, initialized to false
      
      queue = new Queue()
      
      distance[source] = 0
      visited[source] = true
      queue.enqueue(source)
      
      WHILE queue is not empty:
        u = queue.dequeue()
        
        FOR each neighbor v of u:
          IF visited[v] == false:
            visited[v] = true
            distance[v] = distance[u] + 1
            predecessor[v] = u
            queue.enqueue(v)
            
      RETURN distance, predecessor // Can be used to reconstruct paths
    ```

4.  **Time Complexity:** O(V + E) (each vertex and edge is visited once).

### D. Dijkstra's Algorithm (Single-Source Shortest Path, Weighted Graph)

1.  **Concept & Use Cases:**
    *   Finds the shortest path from a source vertex `s` to all other vertices in a weighted graph where all edge weights are non-negative.
    *   *Use Cases*: Routing in networks (e.g., IP routing, GPS navigation), finding shortest paths in games or logistics.

2.  **Algorithm (using a Priority Queue):**
    *   *Explanation*: "Dijkstra's algorithm maintains a set of vertices whose shortest path from the source is already known. It iteratively selects the vertex `u` not yet in this set that has the minimum known distance from the source, adds `u` to the set, and updates the distances to `u`'s neighbors (this is called 'relaxing' an edge).
        1.  Initialize distances to all vertices as infinity, and distance to source `s` as 0.
        2.  Create a min-priority queue and add all vertices to it, using their current distance as priority. (Or, add just the source initially).
        3.  While the priority queue is not empty:
            a.  Extract vertex `u` with the minimum distance from the priority queue.
            b.  For each neighbor `v` of `u`:
                i.  If the path to `v` through `u` is shorter than `v`'s current known distance (i.e., `distance[u] + weight(u,v) < distance[v]`):
                    1.  Update `distance[v] = distance[u] + weight(u,v)`.
                    2.  Set `predecessor[v] = u`.
                    3.  Update `v`'s priority in the priority queue (or re-add if it supports decrease-key)."

3.  **Pseudocode (Dijkstra's with Min-Priority Queue):**
    ```pseudocode
    DIJKSTRA(Graph G, Vertex source):
      distance = array of size G.num_vertices, initialized to INFINITY
      predecessor = array of size G.num_vertices, initialized to NULL
      
      distance[source] = 0
      
      // Min-priority queue stores pairs (distance, vertex_id)
      // or just vertex_id if PQ can look up distances
      pq = new MinPriorityQueue()
      pq.add(source, 0) // Add source with distance 0
      
      // Initialize PQ with all vertices if needed, or add as discovered
      // For simplicity, assume we add to PQ as we discover/update them.
      // Some implementations add all vertices initially with distance INFINITY.
      
      WHILE pq is not empty:
        u = pq.extractMin() // Extracts vertex with smallest distance
        
        // If u has already been "settled" (visited and processed via PQ), skip.
        // This handles cases where a vertex might be added multiple times to PQ
        // with different distances before its final shortest path is found.
        // A 'visited' or 'settled' array can track this.
        // IF u is already settled: CONTINUE

        // Mark u as settled.

        FOR each neighbor v of u with edge weight w(u,v):
          IF distance[u] + w(u,v) < distance[v]:
            distance[v] = distance[u] + w(u,v)
            predecessor[v] = u
            // If v is already in pq, update its priority (decrease-key)
            // Else, add v to pq with new distance
            pq.addOrUpdate(v, distance[v]) 
            
      RETURN distance, predecessor
    ```
    *Note on Priority Queue*: Efficient implementations use a priority queue that supports a `decreaseKey` operation. If not available, re-adding with the new, shorter distance works but might be slightly less efficient. Java's `PriorityQueue` doesn't have a direct `decreaseKey`; a common workaround is to add the new (vertex, distance) pair and let the PQ manage duplicates, always processing the one with the smallest distance first. When extracting, if a vertex has already been finalized (its shortest path found), ignore subsequent occurrences from the PQ.

4.  **Time Complexity:**
    *   With a binary heap based priority queue: O((V + E) log V) or O(E log V) if E > V.
    *   With a Fibonacci heap based priority queue: O(V log V + E) (amortized).
    *   If using an adjacency matrix and scanning for min distance: O(V^2).

## VIII. General Tips for the Exam

### A. Explaining Functionality
*   **Be Clear and Concise:** Start with a high-level definition.
*   **Key Properties:** Mention the defining characteristics.
*   **How it Works (Conceptually):** Explain the logic behind its operations without getting lost in minute code details initially. Use analogies if helpful.
*   **Why it's Useful:** Briefly state its advantages or common use cases.

### B. Implementing Methods
*   **Understand the Goal:** What should the method achieve? What are its inputs and expected outputs?
*   **Edge Cases:** Think about empty structures, null inputs, boundaries (e.g., inserting at start/end of a list, deleting root of a tree).
*   **Core Logic:**
    *   For array-based structures: Consider index management, shifting elements, resizing.
    *   For node-based structures (linked lists, trees): Focus on pointer manipulation (e.g., `next`, `prev`, `left`, `right`). Draw diagrams to help visualize.
    *   For hash-based structures: Remember `hashCode()`, `equals()`, collision handling, and bucket indexing.
    *   For heaps: Remember `heapifyUp` and `heapifyDown`.
*   **Maintain Invariants:** Ensure the data structure's properties (e.g., BST property, heap property, Red-Black tree rules) are preserved after the operation.
*   **Pseudocode First?:** If writing full Java, it might help to sketch out pseudocode or steps on scratch paper.
*   **Java Specifics:** Use correct Java syntax. For collections, know the methods of `List`, `Set`, `Map`, `PriorityQueue`.

### C. Time Complexity Analysis
*   **Identify Dominant Operations:** What part of the algorithm takes the most time? (e.g., loops, recursive calls).
*   **Best, Average, Worst Case:** Understand how input data can affect performance.
    *   BST: balanced vs. skewed.
    *   HashMap/HashSet: good hash distribution vs. many collisions.
*   **Common Complexities:** O(1), O(log n), O(n), O(n log n), O(n^2). Know what they mean.
*   **Amortized Analysis:** Understand it for operations like `ArrayList.add()` or hash table resizing, where expensive operations happen occasionally but average out to be cheap.

Good luck with your final exam!