<?php
/**
 * Created by PhpStorm.
 * @author Yann Le Scouarnec <bunkermaster@gmail.com>
 * Date: 22/11/2017
 * Time: 15:46
 */
try {
    $conn = new PDO('mysql:host=mysql:3306;dbname=demo-garage-lol', 'root', 'root');
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die("Arrrrrghhhh X_x");
}
