

To get the photos page working you have to do the following:

1)go to guide below and edit your tomcat server.xml. http://www.moreofless.co.uk/static-content-web-pages-images-tomcat-outside-war/ You must point it to a local directory on your machine where the images are uploaded to
....change docBase to images folder on your machine, set path="/images"

2)in gardenpeople.servlet.ProfilePhotosServlet change UPLOAD_PATH to the path to images folder. Restart tomcat. Now it should work!

Things to do...

1)Need a button like a red cross that appears on image on hover event that can delete the photo. The database has a table with the paths of images, then we need to delete the path in the database. Do this in ImageDAO

2)click on photo and blow it up like a popup (pretty sure that can javascript or css we dont have to create a new page)

3)find a gardener page. write query in PublicProfileDAO to return a list of profiles for search with postcode and the advanced search filters. gardeners profiles use only latitude and longitude, there is no county anymore so we need some distance calculation. please use mysql workbench (windows) or msqlpro (mac) and go into the database to have a look at the tables. database connection details in ConnectionFactory class.

4)need the jsp for the results of the above page. Are we going to paginate them? how to do that.

5)set up reviews (need table and DAO object with CRUD)

6) html can someone make all pages consistent with the navbar and links and stuff? plus make the forms look a bit better.
