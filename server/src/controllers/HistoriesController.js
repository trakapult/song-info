const { History, Song } = require('../models')

module.exports = {
    async index (req, res) {
        try {
            const userId = req.user.id
            const histories = await History.findAll({
                where: {UserId: userId},
                order: [['updatedAt', 'DESC']],
                include: [{model: Song}]
            })
            res.send(histories)
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
            const history = (await History.findOne({
                where: {
                    UserId: userId,
                    SongId: songId
                }
            }))
            if (history) {
                console.log("\n\n\n", history.createdAt, history.updatedAt, "\n\n\n")
                await history.destroy()
            }
            const newHistory = await History.create({
                UserId: userId,
                SongId: songId
            })
            console.log("\n\n\n", newHistory.createdAt, newHistory.updatedAt, "\n\n\n")
            res.send(newHistory)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to create the history'
            })
        }
    }
}