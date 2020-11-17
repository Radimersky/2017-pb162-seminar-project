## Osmá iterace

Cvičení zaměřené na práci s uspořádanými kolekcemi (a lambda výrazy).

V předchozích iteracích jsme vytvořili několik variant jednoduchých n-úhelníků, jejichž topologie
(pořadí propojení vrcholů hranami) bylo dáno pořadím vrcholů. Nyní si vytvoříme n-úhelník s pojmenovanými vrcholy.
Topologie bude dána abecedním pořadím názvů vrcholů.
Změnou pojmenování vrcholů tak můžeme snadno změnit topologii n-úhelníka.

Ukažme jsi příklad. Na následujícím obrázku je vlevo n-úhelník se šesti vrcholy.
Čísla u vrcholů představují pořadí, ve kterém byly vrcholy definovány a topologie je dána jejich pořadím.
Pokud je topologie dána pojmenováním vrcholů, pak stejného výsledku dosáhneme
pojmenováním vrcholů 1-6 písmeny A-F (obrázek uprostřed).
Přejmenování vrcholů ale můžeme zcela změnit topologii bez nutnosti měnit pořadí samotných vrcholů n-úhelníka
(obrázek vpravo).

![topologie](images/08a.png)

1.  Definujte přirozené uspořádání na třídě `Vertex2D` v souladu s metodou `equals()`,
    tj. třídí se podle souřadnice X vzestupně a v případě shody se třídí podle Y vzestupně.

2.  Vytvořte `VertexInverseComparator` pro třídu `Vertex2D` v balíku `cz.muni.fi.pb162.project.comparator`.
    Komparátor bude vrcholy třídit **sestupně**, nejprve setřídí podle souřadnice X sestupně
    a v případě shody se třídí podle Y sestupně.

3.  Pomocí **uspořádané mapy** vytvořte třídu `LabeledPolygon` rozšiřující třídu `SimplePolygon`.
    Tato třída bude podobná třídám `ArrayPolygon` a `CollectionPolygon` s tím rozdílem,
    že vrcholy jsou uloženy pod svými názvy.

    Vrcholy jsou pojmenovány libovolným textovým řetězcem (nejčastěji jedním písmenem)
    a platí, že *jméno vrcholu je v rámci n-úhelníka unikátní*.
    N-úhelník však může obsahovat dva různě pojmenované vrcholy se stejnými souřadnicemi
    (viz situace na výše uvedeném příkladu).

    Pořadí vrcholů v n-úhelníku je dáno jejich pojmenováním (lexikograficky vzestupně).

    Třída bude mít bezparametrický konstruktor, do konstruktoru předka dejte prázné pole.
    Polygony se vytvoří pomocí metody `addVertex`.

    Implementujte nasledující rozhraní (pro více informací viz JavaDoc daného rozhraní).

    Metody z rozhraní `Polygon`:
    *   `Vertex2D getVertex(int index)` vrátí index-tý vrchol vzhledem k pořadí danému pojmenováním vrcholů.
        Pokud např. máme vrcholy "A", "B" a "C", tak nultý vrchol je "A",
        první vrchol "B", druhý vrchol "C", třetí vrchol opět "A" (modulo) apod.
    *   `int getNumVertices()` vrátí počet vrcholů v kolekci.
    *   `getVertices()` vrátí kolekci vrcholů.

    Implementujte rozhraní `Labelable`:
    *   `void addVertex(String label, Vertex2D vert)` uloží vrchol pod daným názvem.
        Název ani vrchol nesmí být `null`, jinak metoda selže s vhodnou výjimkou.
        Pokud již pod daným názvem vrchol v n-úhelníku existuje, vymění se za nový.
    *   `Vertex2D getVertex(String label)` vrátí souřadnice vrcholu se jménem `label`.
        Metoda vyhodí výjimku `NullPointerException` pokud takový vrchol neexistuje.
    *   `getLabels()` vrátí kolekci názvů vrcholů uspořádaných lexikograficky **vzestupně**.
    *   `getLabels(Vertex2D vertex)` vrátí všechna jména vrcholů se souřadnicemi `vertex`.
        Pokud žádný takový vrchol neexistuje, vrátí prázdnou kolekci.

    Implementujte rozhraní `Sortable`:
    *   `Collection<Vertex2D> getSortedVertices()` vrátí vrcholy setříděné podle přirozeného uspořádání bez duplicit.
    *   `Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator)` vezme libovolný komparátor 2D vrcholů
        a vrátí vrcholy setříděné podle daného komparátoru bez duplicit.

    Nakonec metoda `Collection<Vertex2D> duplicateVertices()` vrátí množinu vrcholů,
    které jsou v polygonu vícekrát pod různým názvem.
> Pokud nevracíme novou kolekci, je potřeba vrátit kolekcii jako nemodifikovatelnou.
4.  Pokud jste implementaci provedli bez chyb, tak po spuštění třídy `Draw`
    se na obrazovce vykreslí [polygon s pojmenovanými vrcholy
    ](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images#iteration-08)
    podobný prostřednímu polygonu nahoře.


## Devátá iterace

Cvičení zaměřené na práci se vstupem a výstupem.

Upravte třídu `LabeledPolygon` tak, aby implementovala rozhraní `PolygonIO`.

1.  Metoda `read(InputStream)` vezme otevřený vstup obsahující pojmenované vrcholy,
    vrcholy načte a přidá je k existujícím vrcholům polygonu.
    Při jakékoliv chybě vstupu/výstupu nebo chybě formátu vstupních dat musí metoda atomicky selhat
    a vyhodit `IOException`. (atomicky = načítám všechno nebo nic)
    Formát vstupních dat je následující:
    *   Vstup je textový.
    *   Co vrchol, to jeden řádek.
    *   Každý řádek je ve formátu _"x y nazev vrcholu"_, tj. nejprve souřadnice vrcholu oddělené mezerou
        a poté název vrcholu (název může obsahovat mezery).
        Viz např. soubor _polygon-ok.txt_.

2.  Metoda `write(OutputStream)` zapíše vrcholy do daného výstupního proudu.
    Výstupní formát je stejný jako pro předchozí metodu.

3.  Metody `write(File)` a `read(File)` budou fungovat stejně jako předchozí,
    budou ale pracovat se souborem namísto vstupně-výstupního proudu.
    Vyhněte se opakování kódu!

4.  Vytvořte metodu `binaryWrite(OutputStream os)`, která bude do výstupního proudu zapisovat přímo,
    bez obalování writerem a přesto bude výsledek textový. Nápověda:
    *   Všechny souřadnice a ostatní text, který chceme zapsat binárně, musíme převést na pole bajtů.
        Třída `String` na to má přímo metodu.
    *   Je třeba vložit univerzální oddělovač konců řádků, `System.lineSeparator()`.
        Také znaky oddělovače konců řádků je nutné převést na bajty.

5.  Spuštěním třídy `Draw` se načte _polygon-ok.txt_ a [vykreslí se na obrazovce
    ](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images#iteration-09).

> Testy vytvářejí soubory `polygon-out.txt` a `polygon.bin` (jehož obsah by měl být při správné implementaci čitelný).
