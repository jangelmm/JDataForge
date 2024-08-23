
# JDataForge

JDataForge es una librería de Java que proporciona una variedad de estructuras de datos, incluyendo pilas, colas, listas enlazadas, árboles binarios y árboles AVL. Esta librería está diseñada para ser flexible y fácil de usar en tus proyectos de programación.

## Tabla de Contenidos

- [Características](#características)
- [Instalación](#instalación)
- [Uso](#uso)
  - [ForgeStack](#forgestack)
  - [ForgeQueue](#forgequeue)
  - [ForgeSequence](#forgesequence)
  - [ForgeDual](#forgedual)
  - [ForgeRing](#forgering)
  - [ForgeChain](#forgechain)
  - [ForgeBinaryTree](#forgebinarytree)
  - [ForgeAVLTree](#forgeavltree)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- **Pilas** (`ForgeStack`): Estructura de datos LIFO (Last In, First Out).
- **Colas** (`ForgeQueue`): Estructura de datos FIFO (First In, First Out).
- **Listas Enlazadas** (`ForgeSequence`, `ForgeDual`, `ForgeRing`, `ForgeChain`): Variantes de listas con diferentes comportamientos y estructuras.
- **Árboles Binarios** (`ForgeBinaryTree`): Estructura de datos con nodos organizados en un formato jerárquico.
- **Árboles AVL** (`ForgeAVLTree`): Un tipo especial de árbol binario autobalanceado.

## Instalación

### Obtener `.jar`
Puedes descargar el archivo `JDataForge.jar`y agregarlo directamente en tus proyectos.

Si genera error al usarlo, te recomiendo compilarlo.

### Compilar
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/JDataForge.git
   ```

2. Compila el proyecto usando Ant:
   ```bash
   cd JDataForge
   ant build
   ```

3. Encuentra el archivo JAR en el directorio `dist`:
   ```bash
   dist/JDataForge.jar
   ```

4. Agrega el archivo JAR a tu proyecto Java.

## Uso

### `ForgeStack`

```java
import com.jangelmm.dataforge.basics.ForgeStack;

public class TestForgeStack {
    public static void main(String[] args) {
        ForgeStack<Integer> stack = new ForgeStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek());
        stack.traverseStack();
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
```

### `ForgeQueue`

```java
import com.jangelmm.dataforge.basics.ForgeQueue;

public class TestForgeQueue {
    public static void main(String[] args) {
        ForgeQueue<Integer> queue = new ForgeQueue<>();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front element: " + queue.peekFront());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
```

### `ForgeSequence`

```java
import com.jangelmm.dataforge.list.ForgeSequence;

public class TestForgeSequence {
    public static void main(String[] args) {
        ForgeSequence<Integer> sequence = new ForgeSequence<>();

        sequence.insert(5);
        sequence.insert(2);
        sequence.insert(9);

        sequence.printAscending();
        sequence.printDescending();
        System.out.println(sequence.contains(9));
        sequence.removeFirst();
        sequence.removeLast();
        sequence.removeAt(1);
    }
}
```

### `ForgeDual`

```java
import com.jangelmm.dataforge.list.ForgeDual;

public class TestForgeDual {
    public static void main(String[] args) {
        ForgeDual<Integer> list = new ForgeDual<>();

        list.insertAtStart(3);
        list.insertAtEnd(4);

        list.printListAscending();
        list.removeHead();
        list.removeTail();
        list.removeElement(2);
    }
}
```

### `ForgeRing`

```java
import com.jangelmm.dataforge.list.ForgeRing;

public class TestForgeRing {
    public static void main(String[] args) {
        ForgeRing<Integer> ring = new ForgeRing<>();

        ring.insertAtEnd(10);
        ring.insertAtBeginning(5);
        ring.insertAtPosition(25, 3);

        ring.printData();
        ring.removeFirst();
        ring.removeLast();
        ring.removeAtPosition(2);
        ring.removeBefore(25);
    }
}
```

### `ForgeChain`

```java
import com.jangelmm.dataforge.list.ForgeChain;

public class TestForgeChain {
    public static void main(String[] args) {
        ForgeChain<Integer> chain = new ForgeChain<>();

        chain.addFirst(10);
        chain.addLast(20);
        chain.addSortedUnique(15);

        chain.traverse();
        chain.removeFirst();
        chain.removeLast();
        chain.reverse();
        System.out.println(chain.contains(15));
        System.out.println(chain.indexOf(15));
    }
}
```

### `ForgeBinaryTree`

```java
import com.jangelmm.dataforge.tree.ForgeBinaryTree;

public class TestForgeBinaryTree {
    public static void main(String[] args) {
        ForgeBinaryTree<String> tree = new ForgeBinaryTree<>();

        tree.add("50");
        tree.add("30");
        tree.add("70");

        tree.printTree();
        tree.inOrderTraversal();
    }
}
```

### `ForgeAVLTree`

```java
import com.jangelmm.dataforge.tree.ForgeAVLTree;

public class TestForgeAVLTree {
    public static void main(String[] args) {
        ForgeAVLTree<String, String> avl = new ForgeAVLTree<>();

        avl.insert("AA", "value9");
        avl.insert("AB", "value15");

        avl.printTree();
        avl.preOrderTraversal();
        avl.inOrderTraversal();
    }
}
```

## Otros enlaces

Si quieres la versión con Maven puedes visitar: https://github.com/jangelmm/DataForge.git

## Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/mi-nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva característica'`).
4. Haz push a tu rama (`git push origin feature/mi-nueva-caracteristica`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENCE) para más detalles.

---

¡Gracias por usar JDataForge! Si tienes alguna pregunta o encuentras algún problema, no dudes en abrir un issue en GitHub.
