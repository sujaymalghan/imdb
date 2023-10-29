package com.example.moviedetails

import android.os.Parcel
import android.os.Parcelable

data class MovieData(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        TODO("genre_ids"),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeInt(id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieData> {
        override fun createFromParcel(parcel: Parcel): MovieData {
            return MovieData(parcel)
        }

        override fun newArray(size: Int): Array<MovieData?> {
            return arrayOfNulls(size)
        }
    }
}

val movies = """
    [
     {
            "adult": false,
            "backdrop_path": "/H6j5smdpRqP9a8UnhWp6zfl0SC.jpg",
            "genre_ids": [
                28,
                878,
                12
            ],
            "id": 565770,
            "original_language": "en",
            "original_title": "Blue Beetle",
            "overview": "Recent college grad Jaime Reyes returns home full of aspirations for his future, only to find that home is not quite as he left it. As he searches to find his purpose in the world, fate intervenes when Jaime unexpectedly finds himself in possession of an ancient relic of alien biotechnology: the Scarab.",
            "popularity": 1973.926,
            "poster_path": "/mXLOHHc1Zeuwsl4xYKjKh2280oL.jpg",
            "release_date": "2023-08-16",
            "title": "Blue Beetle",
            "video": false,
            "vote_average": 7.1,
            "vote_count": 646
        },
        {
            "adult": false,
            "backdrop_path": "/iIvQnZyzgx9TkbrOgcXx0p7aLiq.jpg",
            "genre_ids": [
                27,
                53
            ],
            "id": 1008042,
            "original_language": "en",
            "original_title": "Talk to Me",
            "overview": "When a group of friends discover how to conjure spirits using an embalmed hand, they become hooked on the new thrill, until one of them goes too far and unleashes terrifying supernatural forces.",
            "popularity": 1915.126,
            "poster_path": "/kdPMUMJzyYAc4roD52qavX0nLIC.jpg",
            "release_date": "2023-07-26",
            "title": "Talk to Me",
            "video": false,
            "vote_average": 7.3,
            "vote_count": 732
        },
        {
            "adult": false,
            "backdrop_path": "/ctMserH8g2SeOAnCw5gFjdQF8mo.jpg",
            "genre_ids": [
                35,
                12,
                14
            ],
            "id": 346698,
            "original_language": "en",
            "original_title": "Barbie",
            "overview": "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
            "popularity": 1655.953,
            "poster_path": "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg",
            "release_date": "2023-07-19",
            "title": "Barbie",
            "video": false,
            "vote_average": 7.3,
            "vote_count": 4824
        },
        {
            "adult": false,
            "backdrop_path": "/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
            "genre_ids": [
                28,
                878,
                27
            ],
            "id": 615656,
            "original_language": "en",
            "original_title": "Meg 2: The Trench",
            "overview": "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            "popularity": 1627.214,
            "poster_path": "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            "release_date": "2023-08-02",
            "title": "Meg 2: The Trench",
            "video": false,
            "vote_average": 7,
            "vote_count": 1853
        },

        {
            "adult": false,
            "backdrop_path": "/53z2fXEKfnNg2uSOPss2unPBGX1.jpg",
            "genre_ids": [
                27,
                9648,
                53
            ],
            "id": 968051,
            "original_language": "en",
            "original_title": "The Nun II",
            "overview": "In 1956 France, a priest is violently murdered, and Sister Irene begins to investigate. She once again comes face-to-face with a powerful evil.",
            "popularity": 1241.061,
            "poster_path": "/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg",
            "release_date": "2023-09-06",
            "title": "The Nun II",
            "video": false,
            "vote_average": 6.6,
            "vote_count": 250
        },
        {
            "adult": false,
            "backdrop_path": "/iiXliCeykkzmJ0Eg9RYJ7F2CWSz.jpg",
            "genre_ids": [
                28,
                9648,
                53,
                80
            ],
            "id": 762430,
            "original_language": "en",
            "original_title": "Retribution",
            "overview": "When a mysterious caller puts a bomb under his car seat, Matt Turner begins a high-speed chase across the city to complete a specific series of tasks. With his kids trapped in the back seat and a bomb that will explode if they get out of the car, a normal commute becomes a twisted game of life or death as Matt follows the stranger's increasingly dangerous instructions in a race against time to save his family.",
            "popularity": 1928.276,
            "poster_path": "/oUmmY7QWWn7OhKlcPOnirHJpP1F.jpg",
            "release_date": "2023-08-23",
            "title": "Retribution",
            "video": false,
            "vote_average": 6.6,
            "vote_count": 250
        },
     
       
     ]

""".trimIndent()
