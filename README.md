project url: https://github.com/jaykangheo/demo

# demo


post
- localhost:8080/v2/post/blog
  - request
    - title: String
    - blogName: String
    - thumbNail
    - url
    - contents
  - response
    - 

get
- localhost:8080/v2/search/blog
  - request
    - query
    - sort
    - size
    - page
  - response
    - SearchBlog
      - meta: Meta
        - 
      - documents: List<Document>
        - Document
          - title: String
          - blogName: String
          - thumbNail
          - url
          - contents

to run this project with pre-built jar file, locate the jar file in build/libs/demo2-0.0.1-SNAPSHOT-jar
