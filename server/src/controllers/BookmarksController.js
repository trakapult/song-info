const { Bookmark, Song } = require('../models')

module.exports = {
    async index (req, res) {
        const userId = req.user.id || null // from jwt, proved to be valid
        const songId = req.query.songId || null
        where = {}
        if (userId) {
            where.UserId = userId
        }
        if (songId) {
            where.SongId = songId
        }
        try {
            const bookmarks = await Bookmark.findAll({
                where: where,
                order: [['updatedAt', 'DESC']],
                include: [{model: Song}]
            })
            res.send(bookmarks)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to fetch the bookmarks'
            })
        }
    },
    async post (req, res) {
        try {
            const userId = req.user.id
            const songId = req.body.songId
            const bookmark = (await Bookmark.findOne({
                where: {
                    UserId: userId,
                    SongId: songId
                }
            }))
            if (bookmark) {
                return res.status(400).send({
                    error: 'You already have this set as a bookmark'
                })
            }
            const newBookmark = await Bookmark.create({
                UserId: userId,
                SongId: songId
            })
            res.send(newBookmark)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to create the bookmark'
            })
        }
    },
    async delete (req, res) {
        try {
            const userId = req.user.id
            const bookmarkId = req.params.bookmarkId
            const bookmark = await Bookmark.findOne({
                where: {
                    id: bookmarkId,
                    UserId: userId
                }
            })
            console.log(bookmark)
            if (bookmark) {
                await bookmark.destroy()
                res.send(bookmark)
            } else {
                res.status(500).send({
                    error: 'An error has occured trying to delete the bookmark'
                })
            }
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to delete the bookmark'
            })
        }
    }
}