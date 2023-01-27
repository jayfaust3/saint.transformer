package com.sg.wrapper

import org.apache.spark.sql.SparkSession

/**
 * A trait to get spark sessions.
 */
trait SparkSessionWrapper {

    /*
    Get spark session for local testing
    */
    lazy val spark: SparkSession = SparkSession
        .builder
        .appName("Testing")
        .master("local[*]")
        //to fix issue of port assignment on local
        .config("spark.driver.bindAddress", "localhost")
        .getOrCreate()

    val hadoopConfig = spark.sparkContext.hadoopConfiguration

    hadoopConfig.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName)

    hadoopConfig.set("fs.file.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName)
}
